# Using Spring JdbcTemplate with JdbcTemplateMapper #
This is a tutorial on how to use JdbcTemplateMapper.

Github project for [JdbcTemplateMapper](https://github.com/jdbctemplatemapper/jdbctemplatemapper)

The example code for this tutorial is in class [TutorialTest.java](src/test/java/io/github/ajoseph88/jdbctemplatemapper/test/TutorialTest.java)

The tests go against a MySQL database so you will need one installed and running. Also you will need to create a database named 'tutorial'. See [application.properties](src/test/resources/application.properties) file if you need to change the userid/password for the database. The tables needed for the tests are created by flyway when the tests are run so no need to manually create them.

You can run the test from the command line with:

```
mvn clean package
```

You should see all the SQL being issued on the console.


To see how JdbcTemplateMapper is configured take a look at file [JdbcTemplateMapperConfig.java](src/test/java/io/github/ajoseph88/jdbctemplatemapper/config/JdbcTemplateMapperConfig.java)


