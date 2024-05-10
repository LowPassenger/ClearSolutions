# ClearSolutions
Little part of REST API application

# Endpoints

There are five endpoints present in UserController class:

root URL is /api/v1/users

- **POST request** to root URL for creating new User with JSON like main/test/resources/first_user.json as Request Body (without id parameters)
- **PUT request** with /{id} parameter for updating most fields of existing User with {id}
- **PATCH request** with /{id} parameter for updating some fields of existing User with {id}
- **DELETE request** with /{id} parameter for deleting existing user with {id}
- **GET request** with two UserBirthDate parameters: _from_ and _to_ for getting list of Users with birthdays dates between this two parameters

Pattern for the dates of birth is dd-MM-yyyy

Application has inner validation for:
- email correctness
- phone number correctness
- date of user birth correctness
- check is user age greater or equals 18

Application has logging. Log file output.log place in /clear_solutions folder in your LOG_PATH directory

