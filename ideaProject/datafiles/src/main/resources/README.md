说明
=
1.文件上传的例子
-
2.支付宝支付的例子
-
3.微信支付的例子
-
4.构思一个统一登录管理系统数据库:
-
### 关于表和字段的命名规则的说明:
      表名称: tb_aa,tb_xx
      字段名:aa_bb,xx_cc
      另一张表中的字段 relation_xx_cc
####    4.1  用户表(tb_user):

        user_id(唯一标识),
        user_username(用户名,登录名),
        user_realname(真实名称),
        user_gender(性别),
        user_age(年龄),
        user_phone(电话),
        user_email(邮箱),
        user_idnum(身份证),
        user_type(用户类型,预留,可能有用)
        user_createtime(用户创建时间)
####    4.2  角色表(tb_role):

        role_id(唯一标识),
        role_name(角色名),
        role_code(角色编码),
        role_description(角色描述)
        role_createtime(角色创建时间)
####    4.3  权限表(tb_permission):

        permission_id(唯一标识),
        permission_code(权限编码),
        permission_name(权限名称,主要用来显示),
        permission_description(权限描述),
        permission_type(权限类型,功能,系统权限),
        permission_url(跳转的url)
        permission_createtime(权限添加时间)
        permission_userful(该权限是否有效,返回200就表示有效)
####    4.4  角色_权限_关系表(tb_permissionandrole):

        permissionandrole_id(唯一标识),
        relation_role_id(角色id),
        relation_permission_id(权限id)
####    4.5  用户_角色_关系表(tb_userandrole):

        permissionandrole_id(唯一标识),
        relation_role_id(角色id),
        relation_user_id(用户id)
        
        
