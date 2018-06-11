[#--公共宏定义存放--]
[#--js引入封装--]
[#macro jsImport path isExternal=false]
    [#--是否外部链接--]
    [#if (isExternal)??&&isExternal]
    <script src="${path}?ver=${staticVersion!}"></script>
    [#else]
    <script src="${baseResourcePath}${path}?ver=${staticVersion!}"></script>
    [/#if]
[/#macro]
[#--css引入封装--]
[#macro cssImport path isExternal=false]
    [#--是否外部链接--]
    [#if (isExternal)??&&isExternal]
    <link rel="stylesheet" href="${path}?ver=${staticVersion!}">
    [#else]
    <link rel="stylesheet" href="${baseResourcePath}${path}?ver=${staticVersion!}">
    [/#if]
[/#macro]