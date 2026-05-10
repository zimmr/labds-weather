# API DOCUMENTATION

## ENDPOINTS

### GET /geo

#### Input from query
| Field       | Description |
| ----------- | ----------- |
| city        | City name   |

#### Response

**200 OK**
```
[
    {
        "name": "Canoas",
        "lat": -29.9216045,
        "lon": -51.1799525,
        "country": "BR",
        "state": "Rio Grande do Sul"
    }
]
```

**400 Bad Request**
```
{
    "code": 400,
    "Message": "Error message"
}
```
