## jpsite-security-oauth2-open（微服务开放API授权平台）

本项目是一个基于oath2协议的应用，实现的的功能逻辑与[QQ互联](https://wiki.connect.qq.com/)，[微博开放平台](https://open.weibo.com/wiki/%E9%A6%96%E9%A1%B5)类似，都是同一套认证授权流程。

项目结构简单易懂，却不偷工减料，在学习完本Demo后，Demo中的项目代码依然可以被各位学习者copy到公司的生产项目中使用，真正达到学以致用的目的。

### 本演示项目包括功能有
#### 新用户
1. 用户注册自动分配角色权限
2. 用户只能访问自己所拥有的角色权限访问路径
#### 开放平台
1. 用户可以申请获取客户ID和客户密钥
2. 用户可以通过客户ID获取授权码
3. 用户可以通过客户ID和密钥以及授权码获取access token 和referrsh token和scope
4. 同一用户不同来源获取的token不同

#### 资源api服务（order-service/open-api-service）
1. 可自定义配置需授权url
2. 可自定义配置受限url的访问scope
3. 未授权用户或访问权限不足用户，页面提示相信息
4. 用户通过access token 来访问对应url


关于该项目的更多代码讲解和逻辑讲解，请参考=>[微服务开放授权平台设计与实践]()，或浏览作者主页=>[掘金-蒋老湿](https://juejin.im/user/5b6a41ef5188251ac858752a/posts)
