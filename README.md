# PokemonWebApp
This web application is not a functioning game, but a demonstration of the recommended architectural web application
patterns outlined by [Martin Fowler](https://martinfowler.com/) and [Stuart Thiel](https://users.encs.concordia.ca/~sthiel/soen387/Thesis_Stuart.pdf).

Some of these patterns are:
- DB Registry
- Output Mapper
- Input Mapper
- Domain Object
- Front Controller (no permalink file)
- Dispatcher
- Command

The application returns JSON responses and some UI. This is because grading of this project was done by parsing the JSON.

# Run
- Run an instance of MySQL, and change `application.properties` to match your DB name and credentials.
- Create the tables required by the project (Take a look at the Input Mapper for the columns and types you require)
- Add the project to a Tomcat instance and run the server
- Open a browser, and navigate to `localhost:8080` or whichever port your instance is running on


