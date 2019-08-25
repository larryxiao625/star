# CatApi

*第一次写Api，而且明天要去赶高铁，所以甚是简陋。——Meodinger*

---

**参数**

字段| 类型 | 描述
:-: | :-: | :-
name | String | 猫的名字
gender | String | 猫的性别，参数只能为“F”或者“M”，忽略大小写
age | int | 猫的年龄，参数应大于零
breed | String | 猫的品种，可选字段，不填没有影响

**返回值**

字段| 类型 | 描述
:-: | :-: | :-:
statusCode | int | 状态码
errorCode | int | 错误码
result | String | 返回的字符串

[========]

>###Success-Response:


Url：`http://localhost:8080/api/cat?name=Meo&gender=F&age=4`
```json
{
    "statusCode": 200,
    "errorCode": 0,
    "result": "The girl cat is 4 years old. Her name is Meo"
}
```

Url: `http://localhost:8080/api/cat?name=Meo&gender=m&age=1&breed=Tiger`
```json
{
    "statusCode": 200,
    "errorCode": 0,
    "result": "The boy Tiger cat is 1 year old. His name is Meo"
}
```

[========]

>###Fail-Response:

**errorCode=-1**
Url: `http://localhost:8080/api/cat?age=1&gender=m`
```json
{
    "statusCode": 500,
    "errorCode": -1,
    "result": "Name can not be blank."
}
```
**errorCode=-2**
Url: `http://localhost:8080/api/cat?name=Meo&gender=A&age=1`
```json
{
    "statusCode": 500,
    "errorCode": -2,
    "result": "Gender is not legal"
}
```
Url: `http://localhost:8080/api/cat?name=Meo&age=1`
```json
{
    "statusCode": 500,
    "errorCode": -2,
    "result": "Gender can not be blank."
}
```
**errorCode=-3**
Url: `http://localhost:8080/api/cat?name=Meo&gender=m&age=-1`
```json
{
    "statusCode": 500,
    "errorCode": -3,
    "result": "Age can not be less than zero."
}
```

### End