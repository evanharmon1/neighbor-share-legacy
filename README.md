# neighbor-share
### Java Spring Boot webapp for sharing household items with neighbors, friends, and family

A lot of people buy things like lawncare equipment, tools, camping equipment, cooking utensils, etc. that they use only occasionally and the items just sit there unused most of the time. This wastes money, takes up space, and isn't efficient. If people could easily show what items they have that others could borrow it would save a lot of money and be a more efficient use of everyone's resources. Users can create an account, add items to share, and see what items other users have offered to share from their household. 

## Features

- User Login
  - Users will be able to create a profile and login. They will be able to add a contact email for notifications and requests. Their user profile will list their sharing/borrowing history. Passwords will be salted and hashed.
- Create, Edit, Delete, and View Items
  - Users will be able to add items they are willing to share. Item details will include title, description, picture, availability, notes, category.
- Browse Items
  - Users of the site will be able to browse items added to the database by user, category, or all.
- Checkout Functionality
  - Users will be able to select an item they want to borrow and an email will be sent to the owner letting them know that they would like to borrow that item. User will be able to specify when they would like the item.
