
CS3354 - Fall 2024

UML Sequence Diagram


## **Sequence Diagram Description**

#### Account Creation, Account && Song

These components illustrate the interactions between the user, and the system, during the following actions:

- Creating an account
- Accessing an existing account
- Interacting with a song

In the account creation flow, the user sends a `createAccount` request to the `CreateAccount` Component, which then checks if the username is available, validates the username and password, and creates the account. During the login process (Account), the user will send a `login` request to the `Account` component which validates the information passed in the request (username + password). For song playback, library population, and filtering, the user receives a list of songs from the `Song` component to enable playback, filtering, etc.