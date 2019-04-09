# neighbor-share
### Java Spring Boot webapp for sharing household items with neighbors, friends, and family

A lot of people buy things like lawncare equipment, tools, camping equipment, cooking utensils, etc. that they use only occasionally and the items just sit there unused most of the time. This wastes money, takes up space, and isn't efficient. If people could easily show what items they own that others could borrow, it would save a lot of money and be a more efficient use of everyone's resources. Users can create an account, add items to share, and see what items other users have offered to share. Users can type a search term or browse shared items by category or owner and can click a button to automatically generate an email notifying the owner of the user's desire to borrow that item.

## Features

- User Profiles
  - Users can create a profile and login. They can share a contact email for notifications and requests.
  - Their user profile displays their account information which can be edited.
  - Their profile page lists the items they have added to the site.
  - Users can delete their account which will automatically delete their shared items.
- Create, Edit, Delete, & View
  - Users can add items they are willing to share. Item details include title, description, category, owner, and an image of the item.
  - Users can browse items added to the site by owner, category, or list all items on the site via dropdown menus
  - Item details can be edited by the user.
  - Users can delete items they have added.
- Item Images
  - Users can upload their own image of the item and can change the image or delete the image.
- Checkout Functionality
  - Users can select an item they want to borrow and click a button that automatically generates a dynamic email that is sent to the owner of the item letting them know they would like to borrow that item. The owner can reply directly to the email to communicate with the requester.
- User Interface
  - Robust form validation with dynamic error messages for user registration, editing user details, adding items, and editing items.
  - Users can search for items by typing in a text box that dynamically filters the currently displayed items.
- Secure & Private
  - Authentication and authorization implemented with Spring Security that protects user data and prevents phishing and CSRF attacks.
  - Account passwords are hashed and salted protecting against data leaks.
  - Email messages are sent without exposing any email address to the user – emails are handled on the back-end.
  - AWS S3 image filenames are randomized at creation

## Technology

* Java
* Spring Boot
* Spring Data/JPA/Hibernate/MySQL
* Thymeleaf templates and Bootstrap components
* Spring Security for authenitication and authorization of registered user areas including password hashing and salting for user data privacy and CSRF attack prevention via secure tokens
* Spring Boot Maill for dynamic email message generation and sending
* AWS S3 API for storing user's item images
* Maven for dependency management and build automation

## Deployment

* Ubuntu 18.04 on a Digital Ocean VM
* LAMP stack with NGINX reverse proxy and phpMyAdmin





