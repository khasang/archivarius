# Archivarius - Document Workflow System
### OpenSource Training Project

![picture](https://github.com/khasang-incubator/archivarius/blob/development/src/main/resources/ArchivariusLogo.png)


###*artifiner*
-------------
## Как заливать картинки на GitHub
Загрузка картинок для документации непосредственно в ресурсы проекта приведёт к замусориванию репозитория ненужными файлами и коммитами.
Лучше грузить картинки так, как показано в [этой статье](http://www.devbug.info/2014/11/github.html)

### Database insert example
Добавил пример вставки в базу данных. Сразу с подключенными логами.



###*Eugene NeocortexF*
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
-------------