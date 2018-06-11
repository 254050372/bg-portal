package com.boot.core.common.base;/**
 * @description
 * @autor xbwu on 2017/9/23.
 */

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.DatabaseMetaData;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * jdbcTemplate常用查询封装
 * 也可以直接使用jdbcTemplate的api进行相关操作
 * @author xbwu
 * @create 2017-09-23 
 **/
public class JdbcQueryBuilder {

    private final static String MYSQL_DB="mysql";
    private final static String ORACLE_DB="oracle";
    private final static String SQLSERVER_DB="microsoft sql server";


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public JdbcQueryBuilder(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    /**
     * 分页查询，带占位符查询参数，sql语句内需要用?表示参数占位符
     * 形如：select id from user where id=?
     * @param sql
     *            查询的sql语句
     * @param args
     *            参数
     * @param page
     *            分页类
     * @return 返回 List<Map<String, Object>>
     */
    public List<Map<String, Object>> queryPage(String sql, Pages page, Object[] args) throws Exception{
        if (page.getStartRow() <= 0 && page.getEndRow() <= 0) {
            return jdbcTemplate.queryForList(sql,
                    args);
        }
        PageParmBuilder pageParmBuilder = new PageParmBuilder(sql, page, args).invoke();
        sql = pageParmBuilder.getSql();
        args = pageParmBuilder.getArgs();

        logger.info("paging sql : \n {}",sql);
        return jdbcTemplate.queryForList(sql, args);
    }

    /**
     * 分页查询，不带查询参数
     *
     * @param sql
     *            查询的sql语句
     * @param page
     *            分页对象
     * @return  返回 List<Map<String, Object>>
     */
    public List<Map<String, Object>> queryPage(String sql,Pages page) throws Exception{
        return this.queryPage(sql, page,new Object[] {});
    }
    /**
     * 分页查询，不带查询参数
     *
     * @param sql
     *            查询的sql语句
     * @param page
     *            分页对象
     * @param c
     *            返回对象类型
     * @return  返回 List<T>
     */
    public <T> List<T> queryPage(String sql, Pages page, Class<T> c) throws Exception{
        return this.queryPage(sql,page,new Object[]{},c);
    }

    /**
     * 查询单条数据返回指定对象
     * @param sql
     * @param c
     * @param <T>
     * @return 返回 <T>
     * @throws Exception
     */
    public <T> T queryOneByObject(String sql,Class<T> c) throws Exception{
        return queryOneByObject(sql, null,c);
    }

    /**
     * 查询单条数据返回map结果
     * @param sql
     * @param args
     * @return 返回Map<String,Object>
     * @throws Exception
     */
    public Map<String,Object>  queryOneByMap(String sql,Object[] args) throws Exception{
        try {
            return jdbcTemplate.queryForMap(sql, args);
        }catch(EmptyResultDataAccessException e){
            return null;
        }catch(Exception e){
            throw e;
        }
    }

    /**
     * 查询单条数据返回map结果
     * @param sql
     * @return 返回Map<String,Object>
     * @throws Exception
     */
    public Map<String,Object>  queryOneByMap(String sql) throws Exception{
        return queryOneByMap(sql, null);
    }

    /**
     * 查询单独对象返回
     * @param sql
     * @param c
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T queryOneByObject(String sql,Object[] args,Class<T> c) throws Exception{
        if(args!=null){
            Pattern pattern = Pattern.compile("\\?");
            Matcher matcher = pattern.matcher(sql);
            for(int i  =  0 ; i<args.length ;  i++,matcher =  pattern.matcher(sql)){
                sql  =  matcher.replaceFirst(args[i].toString()) ;
            }
        }
        logger.info("paging sql : \n {}",sql);
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(c));
        }catch(EmptyResultDataAccessException e){
            return null;
        }catch(Exception e){
            throw e;
        }
    }
    /**
     * 分页查询，带参数带转换器
     *
     * @param sql
     *            查询的sql语句
     * @param page
     *            分页对象
     * @param   c
     *            返回对象类型
     * @return
     */
    public <T> List<T> queryPage(String sql, Pages page, Object[] args, Class<T> c) throws Exception {
        if (page.getStartRow() <= 0 && page.getEndRow() <= 0) {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper(c));
        }
        PageParmBuilder pageParmBuilder = new PageParmBuilder(sql, page, args).invoke();
        sql = pageParmBuilder.getSql();
        args = pageParmBuilder.getArgs();

        Pattern pattern = Pattern.compile("\\?");

        Matcher matcher = pattern.matcher(sql);

        for(int i  =  0 ; i<args.length ;  i++,matcher =  pattern.matcher(sql)){
            sql  =  matcher.replaceFirst(args[i].toString()) ;
        }

        logger.info("paging sql : \n {}" ,sql);
        return  jdbcTemplate.query(sql, new BeanPropertyRowMapper(c));
    }

    private String getLimitStringByOracle(String sql, boolean hasOffset) {
        sql = sql.trim();
        boolean isForUpdate = false;
        if ( sql.toLowerCase().contains(" for update") ) {
            sql = sql.substring( 0, sql.indexOf(" for update"));
            isForUpdate = true;
        }
        StringBuffer pagingSelect = new StringBuffer( sql.length()+100 );
        if (hasOffset) {
            pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
        }
        else {
            pagingSelect.append("select * from ( ");
        }
        pagingSelect.append(sql);
        if (hasOffset) {
            pagingSelect.append(" ) row_ where rownum <= ?) where rownum_ > ?");
        }
        else {
            pagingSelect.append(" ) where rownum <= ?");
        }
        if ( isForUpdate ) {
            pagingSelect.append( " for update" );
        }
        return pagingSelect.toString();
    }

    private String getLimitStringByMysql(String sql, boolean hasOffset) {
        sql = sql.trim();
        boolean isForUpdate = false;
        if ( sql.toLowerCase().contains(" for update") ) {
            sql = sql.substring( 0, sql.indexOf(" for update"));
            isForUpdate = true;
        }

        StringBuffer pagingSelect = new StringBuffer( sql.length()+100 );
        pagingSelect.append("select a.* from ( ");
        pagingSelect.append(sql);
        pagingSelect.append(" ) a ");
        if (hasOffset) {
            pagingSelect.append(" limit ?,?");
        }
        else {
            pagingSelect.append(" limit ?");
        }
        if ( isForUpdate ) {
            pagingSelect.append( " for update" );
        }
        return pagingSelect.toString();
    }


    private class PageParmBuilder {
        private String sql;
        private Pages page;
        private Object[] args;

        public PageParmBuilder(String sql, Pages page, Object... args) {
            this.sql = sql;
            this.page = page;
            this.args = args;
        }

        public String getSql() {
            return sql;
        }

        public Object[] getArgs() {
            return args;
        }

        public PageParmBuilder invoke() throws Exception{

            getLimitString(false);
            return this;
        }
        private void getLimitString(boolean b) throws Exception{
            final int start= page.getStartRow();
            final int limit=page.getEndRow();
            DatabaseMetaData md = jdbcTemplate.getDataSource().getConnection().getMetaData();
            if(page.isAutoCount()){//是否查询总条数
                StringBuilder countSQL=new StringBuilder();
                countSQL.append(" select count(0) from ( ").append(sql).append(" ) table_count");
                page.setTotalCount(jdbcTemplate.queryForObject(countSQL.toString(),Long.class));
            }
            if(md.getDatabaseProductName().toLowerCase().contains(MYSQL_DB)){
                if (start <= 1) {
                    sql = getLimitStringByMysql(sql, false);
                    args = ArrayUtils.insert(args.length,args, limit);
                } else {
                    sql = getLimitStringByMysql(sql, true);
                    args = ArrayUtils.insert(args.length,args,  start);
                    args = ArrayUtils.insert(args.length,args,  limit);
                }
            }else if(md.getDatabaseProductName().toLowerCase().contains(ORACLE_DB)){
                if (start <= 1) {
                    sql = getLimitStringByOracle(sql, false);
                    args = ArrayUtils.insert(args.length,args, limit);
                } else {
                    sql = getLimitStringByOracle(sql, true);
                    args = ArrayUtils.insert(args.length,args,  limit);
                    args = ArrayUtils.insert(args.length,args,  start);
                }
            }else{
                logger.error("当前分页方法不支持数据库：{}",md.getDatabaseProductName());
                throw new Exception("当前分页方法不支持数据库："+md.getDatabaseProductName());
            }

        }
    }
}
