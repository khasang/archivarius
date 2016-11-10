package io.khasang.archivarius.model;

import io.khasang.archivarius.controller.AppController;
import org.apache.log4j.Logger;

/**
 * Created by Eugene NeocortexF on 08.11.2016.
 * Class provides backup service of PostgerSQL database
 */

/*Комментарий к домашнему заданию:
В wiki.postgresql написано, что для решения задачи бэкапа на разной архитектуре,
используются script файлы, которые являются обычными текстовыми файлами (ASCII text)
и содержат в себе SQL команды, позволяющие вернуть базу в необходимое состояние
на машинах любой архитекруты.
Однако, лаконичного решения ни какого не нашел, за основу взял и модифицировал код из UTask
Так же вызывает сомнение работоспособность кода (нет возможности проверить) под Linux,
потому что необходимо обладать правами superuser*/

public class DatabaseBackup {
    private static final Logger log = Logger.getLogger(DatabaseBackup.class);
    String nameOfDB = "archivarius";
    String hostName = "localhost";
    String userName = "root";
    String password = "root";
    String pathForBackupFile = "C:\\backup\\backup_archivarius.sql";

    public String backupLinuxPostgreSQL() {
        String loginAsSuperuser = "su – postgres";
        String changeDirectory = "cd /opt/PostgresSQL/9.4/bin";
        String path = "./pg_dump";
        String command = path + " -d " + nameOfDB + " -h " + hostName + " -U " + userName + " -w " +
                " -f " + pathForBackupFile;
        String[] environment = {"PGPASSWORD=" + password};
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(loginAsSuperuser);
            runtime.exec(changeDirectory);
            runtime.exec(command, environment);
            return "Backup of database performed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Backup failed: " + e);
            return "Something goes wrong:" + e;
        }
    }

    public String backupWindowsPostgreSQL() {
        String pathToPgdump = "\"C:\\Program Files\\PostgreSQL\\9.4\\bin\\pg_dump.exe\"";
        String command = pathToPgdump + " -d " + nameOfDB + " -h " + hostName + " -U " + userName + " -w " +
                " -f " + pathForBackupFile;
        String[] environment = {"PGPASSWORD=" + password};
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command, environment);
            return "Backup of database performed successfully";
        } catch (Exception e) {
            System.err.println("asdasdasds");
            log.error("code error" + e);
            return "Something goes wrong at backupWindowsPostgreSQL:" + e;
        }
    }
}
