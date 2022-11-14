# dimensa-challenge
#### Author: José Mário R Júnior
## About <a name = "about"></a>

Restful API Contacts - Challenge Dimensa Tecnologia


## Resources

| HTTP/ Method | Description                                 | Path                 |
|--------------|---------------------------------------------|----------------------|
| `GET`        | Return contact or collection of the contact | v1/contacts/{id}     |
| `POST`       | Add new contact                             | v1/contacts          |
| `PUT`        | Update an existing contact                  | v1/contacts/{id}     |
| `DELETE`     | Remove an existing contact                  | v1/contacts/{id}     |

## Technology/ Frameworks

```
   Spring framework
   Maven
   PostgreSQL
   Lombok
   Mockito
   Git
   Heroku
```

## Environment Variables

    API_PORT
    JDBC_DATASOURCE_URL (*Heroku auto configuration)
    POSTGRES_USERNAME
    POSTGRES_PASSWORD
    

## Repository

    github: https://github.com/dev-jmariojr/dimensa-challenge

## Heroku

    app: https://api-challenge-dimensa.herokuapp.com/v1/contacts/info


## Deploy

    . install heroku-cli
    . heroku login (credentials informations) 
    . heroku create NAME_APP
    . heroku addons:create heroku-postgresql:hobby-dev     
    . git push heroku main