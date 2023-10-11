# Using Spring JdbcTemplate with JdbcTemplateMapper #
This is a tutorial on how to use JdbcTemplateMapper which is a wrapper around Spring JdbcTemplate to make CRUD and relationship queries less verbose.

The DZone [article](https://dzone.com/articles/using-spring-jdbctemplate-with-jdbctemplatemapper) corresponding to this tutorial.

Github project for [JdbcTemplateMapper](https://github.com/jdbctemplatemapper/jdbctemplatemapper) library.

The example code for this tutorial is in class [TutorialTest.java](src/test/java/io/github/ajoseph88/jdbctemplatemapper/test/TutorialTest.java).
The tests go against a MySQL database. On your MySQL instance you will need to create a database named 'tutorial'. Make appropriate changes to [application.properties](src/test/resources/application.properties) file so you can connect to your database. The tables needed for the tests are created automatically when the tests are run so no need to manually create them. To see how JdbcTemplateMapper is configured take a look at file [JdbcTemplateMapperConfig.java](src/test/java/io/github/ajoseph88/jdbctemplatemapper/config/JdbcTemplateMapperConfig.java)


You can run the test from the command line with:

```
mvn clean package
```

You should see all the SQL being issued on the console.




