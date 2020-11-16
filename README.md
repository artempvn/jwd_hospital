# Hospital

## Java Web Development
## by Artem Piven

## Roles

There are three available roles:
- admin;
- doctor;
- nurse.

### admin

His task is to take control over personal.<br>
Opportunities:
- register new user with role **doctor** or **nurse**;
- ban and unban users;
- see list of users.

### doctor

His task is to take control over patients.<br>
Opportunities:
- register new patient;
- establish patient's diagnosis;
- make some purpose to patient (including drugs and procedures);
- assign **doctor** or **nurse** to purpose;
- be assigned to purpose;
- see list of patients;
- see list of purposes assigned to this user;
- discharge patient when purpose is comleted.

### nurse

Her task also is to take control over patients, but with limitations in comparison with **doctor**<br> 
Opportunities:
- be assigned to purpose (excluding operation);
- see list of patients;
- see list of purposes assigned to this user.


## Description

**Admin** register new user with role **doctor** or **nurse**.
User can log in after following the link in email, which was sent automatically during registration process. **Doctor** can register patient, established his diagnosis, then make purpose- appoint some drugs and/or procedures/operations, assign personal, who can deal with this purpose. After comliting all steps in purpose, **doctor** can discharge this patient.

## Diagram

![diagram of db](https://github.com/artempvn/jwd_hospital/blob/main/diagram.jpg)
