# Archivarius - Documents Workflow System 
###Open Source Training Project of Khasang Geekteams

![picture](https://github.com/khasang-incubator/archivarius/blob/development/src/main/resources/ArchivariusLogo.png?raw=true)



Archivarius developers team
-------------
###Team lead
**Sergey Kuznetsov** Education and assistance - [Khasang.io][4]

###Developers
**Yuriy Kornienko** Back-end and front-end [dynamost@dynamost.ru][1]

**Eugene Shamkin** Back-end and front-end [eugeneshamkin@mail.ru][2]

**Tatiana Vorobieva** Back-end [t.tianav2@gmail.com][3]


-------------
General description
=================== 
A workflow consists of an orchestrated and repeatable pattern of business activity enabled by the systematic organization of resources into processes that transform materials, provide services, or process information. It can be depicted as a sequence of operations, declared as work of a person or group, an organization of staff, or one or more simple or complex mechanisms.
From a more abstract or higher-level perspective, workflow may be considered a view or representation of real work. The flow being described may refer to a document, service or product that is being transferred from one step to another.

The ```main objective```  is ensuring that the status of work with documents in which each specialist (as head of the company and its ordinary employee) may to obtain at any time without great effort to the necessary documents and necessary information.

Substantially **Archivarius** consist of several modules that it's prefer to user, that will be described below.

-------------
Only secured operations!
=================== 

The security of **Archivarius** project is provided by proven technology - ```Spring Security```. It is a framework of Java that focuses on providing both authentication and authorization to Java applications. Like all Spring projects, the real power of Spring Security is found in how easily it can be extended to meet custom requirements

![Spring Security](https://github.com/khasang/archivarius/blob/development/src/main/resources/Spring-Security-logo.png) 


> **Features:**
> 
> - Comprehensive and extensible support for both Authentication and Authorization
> - Protection against attacks like session fixation, clickjacking, cross site request forgery, etc
> - Servlet API integration
> - Optional integration with Spring Web MVC and much more…

Every person who logged in should be ancored to his personnel data, department and prosition at company, that will helps to restict the access in case of necessaty. 

![Login](https://github.com/khasang/archivarius/blob/development/src/main/resources/login.png) 

-------------
Inbox and outbox documents
=================== 
Basically the ```Inbox``` and ```Outbox``` documents are company log, where company can keep track of what is necessary to prepare for response, and also register a response with attachments for sending it to the appropriate persons.

**Inbox Documents** page contains documents that other people have created for to your attention. You access this list by clicking Inbox Documents in the navigation bar. To view a document, ```just click its name```.

**Outbox Documents** page contains documents that was prepared as answer for some requests in the company. All documents flow are stored in the ```Inbox``` and ```Outbox``` within the database so that they can be re-read or used at any time.

![Main](https://github.com/khasang/archivarius/blob/development/src/main/resources/main.png)


-------------
Internal documents
=================== 

**Internal Documents** page contains documents only for company inside usage. It can be various orders, holiday and vacations schedules, rewarding employees letters and etc.

The main objective is to every employee can access to reliable documentation at every time. Employees receive only the documentation that is designed for them.
 
-------------
Control of documents execution
===================

Controlling documents status and deadlines make possible to identify, evaluate and propose preventative actions giving  **Archivarius** users the tools necessary to meet their objectives, no matter what problems are encountered.

![Control](https://github.com/khasang/archivarius/blob/development/src/main/resources/control.png)

**Control** page contains documents that have deadlines for execution and their status. After work status changes the document changes it's status. Deadline fields in document table have the colored backgrounds depended on renaining time for visual help.
 
-------------
Backup your PostrgeSQL database using Archivarius!
=================== 
![Backup at Linux](https://github.com/khasang-incubator/archivarius/blob/eshamkin/src/main/resources/Images/backup2.png?raw=true) 


###Feature description
The graphical user interface for database administration in Postgres Plus Standard Server is named pgAdmin (Postgres Studio if you are using Advanced Server). The capabilities and appearance of pgAdmin and Postgres Studio are the same, and both give you a quick and easy way to back up and restore Postgres Plus database objects.

The actual backup and restore operations are carried out by the Postgres Plus command line utility programs **pg_dump** and **pg_restore**. When you use pgAdmin to back up or restore database objects, pgAdmin builds and executes a command that calls the **pg_dump** program or the **pg_restore** program with the appropriate parameters. You can view the **pg_dump** or **pg_restore** command built and executed by pgAdmin to help you better understand the backup or restore operation performed, and also to serve as a training aid for running **pg_dump** and **pg_restore** on the command line without using pgAdmin.

*Archivarius web-application* is allows to you dumping your PostreSQL database using special class DatabaseBackup that contains two methods for file dumping at Linux and Windows operating systems that will help you to use **pg_dump** in automatic mode. Let's see it in details:


###Special class for Database Backup 
-------------

At this time class under constuction and provedes you abilities to use PostgreSQL database backup only in hard coded folders with default arguments. 

> **Note:**

> - Don't forget to make backup of you database at the regular basis. It can help you to prevent from losing your data.
> - Your local documents are not located only at your computer, most of it stored at server. Keep calm and  periodically make server's data *archivation*. 
> - Clearing your computer data may **delete all your local documents!** Make sure your documents are synchronized with **Archivarius server**.

### </i> Backup at Linux  ![Backup at Linux](https://github.com/khasang-incubator/archivarius/blob/eshamkin/src/main/resources/Images/linux.png?raw=true) 

The possibility to backup your files at Linux opearting system is represented by the ```backupLinuxPostgreSQL()``` method of ```DatabaseBackup``` class. This method isn't getting any parametrs at this time and returning one of two strings, that indicates result of method usage:
```Backup of database performed successfully```
**or**
```Something goes wrong:``` with log error for tracing root couses.

After usage method will put dumped copy of database at ```home\backup\``` folder.
 

### </i> Backup at Windows ![Backup at Windows](https://github.com/khasang-incubator/archivarius/blob/eshamkin/src/main/resources/Images/windows.png?raw=true)
The possibility to backup your files at Linux opearting system is represented by the ```backupWindowsPostgreSQL()``` method of ```DatabaseBackup``` class. This method isn't getting any parametrs at this time and returning one of two strings, that indicates result of method usage:
```Backup of database performed successfully```
**or**
```Something goes wrong:``` with log error for tracing root couses.

After usage method will put dumped copy of database at ```c:\backup\``` folder.

 [1]: mailto:dynamost@dynamost.ru
 [2]: mailto:eugeneshamkin@mail.ru
 [3]: mailto:t.tianav2@gmail.com
 [4]: Khasang.io