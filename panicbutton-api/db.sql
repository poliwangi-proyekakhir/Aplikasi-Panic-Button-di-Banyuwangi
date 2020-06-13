/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 10.1.35-MariaDB : Database - panicbutton
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`panicbutton` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `panicbutton`;

/*Table structure for table `artikel` */

CREATE TABLE `artikel` (
  `id_artikel` int(11) NOT NULL AUTO_INCREMENT,
  `judul` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isi` text COLLATE utf8_unicode_ci,
  `foto` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tgl_dibuat` date DEFAULT NULL,
  `id_status_artikel` int(11) DEFAULT NULL,
  `id_pengguna` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_artikel`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `artikel` */

insert  into `artikel`(`id_artikel`,`judul`,`isi`,`foto`,`tgl_dibuat`,`id_status_artikel`,`id_pengguna`) values (1,'Kecelakaan di jagorawi','banyak sekali kecelakaan yang teradi akhir-akhir ini',NULL,'2020-04-23',1,1),(2,'truk ban gembo di pinggir jalan','terdapat truk yang berhenti di bahu jalan karena ban nya gembos dan tidak bisa jalan lagi',NULL,'2020-04-23',1,1);

/*Table structure for table `detail_pengguna` */

CREATE TABLE `detail_pengguna` (
  `id_detail_pengguna` int(11) NOT NULL AUTO_INCREMENT,
  `nik` int(11) DEFAULT NULL,
  `nama` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `alamat` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `no_telp` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `tempat_lahir` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `foto_ktp` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `foto_pengguna` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_update` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_detail_pengguna`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `detail_pengguna` */

insert  into `detail_pengguna`(`id_detail_pengguna`,`nik`,`nama`,`alamat`,`no_telp`,`tgl_lahir`,`tempat_lahir`,`foto_ktp`,`foto_pengguna`,`date_created`,`date_update`) values (1,12,'viko anggara','banyuwangi','123321','1991-01-31','banyuwangi',NULL,NULL,'2020-04-23 23:22:28','0000-00-00 00:00:00');

/*Table structure for table `jenis_laporan` */

CREATE TABLE `jenis_laporan` (
  `id_jenis_laporan` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_jenis_laporan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `jenis_laporan` */

/*Table structure for table `kejadian` */

CREATE TABLE `kejadian` (
  `id_kejadian` int(11) NOT NULL AUTO_INCREMENT,
  `tgl_kejadian` date DEFAULT NULL,
  `foto_kejadian` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `latitude` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `longitude` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `keterangan` text COLLATE utf8_unicode_ci,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_update` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `id_jenis_laporan` int(11) DEFAULT NULL,
  `id_pengguna` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_kejadian`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `kejadian` */

/*Table structure for table `level_akses` */

CREATE TABLE `level_akses` (
  `id_level_akses` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_level_akses`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `level_akses` */

insert  into `level_akses`(`id_level_akses`,`nama`) values (1,'masyarakat'),(2,'polisi');

/*Table structure for table `penanganan` */

CREATE TABLE `penanganan` (
  `id_penanganan` int(11) NOT NULL AUTO_INCREMENT,
  `tgl_penanganan` date DEFAULT NULL,
  `id_kejadian` int(11) DEFAULT NULL,
  `id_pengguna` int(11) DEFAULT NULL,
  `id_status_penanganan` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_penanganan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `penanganan` */

/*Table structure for table `pengguna` */

CREATE TABLE `pengguna` (
  `id_pengguna` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_level_akses` int(11) DEFAULT NULL,
  `id_detail_pengguna` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_pengguna`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `pengguna` */

insert  into `pengguna`(`id_pengguna`,`username`,`password`,`id_level_akses`,`id_detail_pengguna`) values (1,'viko','437599f1ea3514f8969f161a6606ce18',1,1);

/*Table structure for table `status_artikel` */

CREATE TABLE `status_artikel` (
  `id_status_artikel` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_status_artikel`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `status_artikel` */

insert  into `status_artikel`(`id_status_artikel`,`nama`) values (1,'Publish'),(2,'Draft');

/*Table structure for table `status_penanganan` */

CREATE TABLE `status_penanganan` (
  `id_status_penangnan` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_status_penangnan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `status_penanganan` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
