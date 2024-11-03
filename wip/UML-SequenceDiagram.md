```mermaid
sequenceDiagram
    % actors
    actor User1

    % participants (objects)
    participant CA as CreateAccount
    participant ACC as Account
    participant Song as Song

    % CreateAccount Flow
    User1 ->> CreateAccount(username, password)
    activate CA

```
