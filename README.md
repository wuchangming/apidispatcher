# apidispatcher

api 根据Rsp_Header 的 cmd值进行分发

##说明
### 1.`@ApiCmdMapping`

用法类似`@RequestMapping`。  
这里`@ApiCmdMapping`只会mapping 到 `/mobile` 路径
```java
//"cmd_name1" 对应cmd的值
@ApiCmdMapping("cmd_name1")  
// 同时mapping到多个cmd
@ApiCmdMapping({"cmd_name1","cmd_name2"})  
```

如果请求找不到相应的cmd会mapping到原来的`@RequestMapping("/mobile")`

### 2.参数传入
类型为Req_header的参数直接传入解析好的Req_header对象  
类型为JSON同时有`@ReqBodyJson`注解的直接传入解析好的reqBodyJson对象  

```java
public @ResponseBody String cmdXHandler(Req_header req_header, @ReqBodyJson JSON reqBodyJson /*... other param:  */){

    // ....;

    return null;
}
```

##Demo
```java
@Controller
public class ApiController1 {

    /**
    *
    * @param req_header 直接传入 {@link Req_header}的参数
    * @param reqBodyJson 直接传入 JSON 格式的reqBodyJson 的参数  (需要加注解 @ReqBodyJson)
    * @param request
    * @param response
    * @return
    */
    @ApiCmdMapping("cmd_name1")   //  "cmd_name1" 对应cmd的值
    public @ResponseBody JSON mobile(Req_header req_header, @ReqBodyJson JSON reqBodyJson, HttpServletRequest request,
    HttpServletResponse response) {
        System.out.println(reqBodyJson);
        return reqBodyJson;
    }

    @ApiCmdMapping("cmd_name2")
    public @ResponseBody String mobile2() {
        return "api response2";
    }

}

```
