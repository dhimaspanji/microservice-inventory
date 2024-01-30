/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 10.4.22-MariaDB : Database - bvk
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bvk` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `bvk`;

/*Table structure for table `tbl_order` */

DROP TABLE IF EXISTS `tbl_order`;

CREATE TABLE `tbl_order` (
  `id_order` varchar(100) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  `total_price` int(11) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `tbl_order` */

insert  into `tbl_order`(`id_order`,`status`,`total_price`,`order_date`,`created_at`,`updated_at`) values 
('5540edd8-de9d-42c2-ac3e-fc0fec3c259d','IN PROGRESS',5000,'2024-01-28 16:44:52','2024-01-28 16:44:52',NULL),
('57800151-25fa-4080-b71e-7661131e64f2','IN PROGRESS',5000,'2024-01-28 16:40:13','2024-01-28 16:40:13',NULL),
('7c044a6e-de8a-400c-9b21-891d542d3793','IN PROGRESS',5000,'2024-01-28 16:16:22','2024-01-28 16:16:22',NULL),
('9542e90f-6f11-4682-9502-1e25facde6f8','IN PROGRESS',5000,'2024-01-28 16:44:44','2024-01-28 16:44:44',NULL),
('c4904ba1-0792-4ea2-8949-4176988cde76','IN PROGRESS',5000,'2024-01-28 15:05:35','2024-01-28 15:05:35',NULL),
('db7c5755-5e1e-4a9e-9f55-ae12e8ae6103','IN PROGRESS',5000,'2024-01-28 16:29:30','2024-01-28 16:29:30',NULL),
('de824e3b-c44a-491f-8a6b-8faa9df9658d','IN PROGRESS',5000,'2024-01-28 16:11:57','2024-01-28 16:11:57',NULL),
('e3e6aa3a-4d13-482a-95bc-fb8b122dd758','IN PROGRESS',5000,'2024-01-28 16:44:36','2024-01-28 16:44:36',NULL),
('ee440770-a3d2-4beb-9409-5ab2cf80c2a2','IN PROGRESS',5000,'2024-01-28 16:30:47','2024-01-28 16:30:47',NULL),
('f85a8d50-8a60-471c-b593-e9c582c3ad24','IN PROGRESS',5000,'2024-01-28 16:30:34','2024-01-28 16:30:34',NULL);

/*Table structure for table `tbl_order_detail` */

DROP TABLE IF EXISTS `tbl_order_detail`;

CREATE TABLE `tbl_order_detail` (
  `id_order_detail` varchar(100) NOT NULL,
  `id_order` varchar(100) DEFAULT NULL,
  `id_product` varchar(100) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id_order_detail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `tbl_order_detail` */

insert  into `tbl_order_detail`(`id_order_detail`,`id_order`,`id_product`,`qty`,`price`,`created_at`,`updated_at`) values 
('1d61be2d-aca7-4956-99bd-12af956a4097','ee440770-a3d2-4beb-9409-5ab2cf80c2a2','1abad4cb-b886-40c1-917e-372c44ebbe05',1,5000,'2024-01-28 16:30:47',NULL),
('2e9ce84d-f4c9-45f0-8b7a-2698bae0cb58','e3e6aa3a-4d13-482a-95bc-fb8b122dd758','1abad4cb-b886-40c1-917e-372c44ebbe05',1,5000,'2024-01-28 16:44:48',NULL),
('58183efe-e413-4b97-9470-56dc7ad5d1e2','db7c5755-5e1e-4a9e-9f55-ae12e8ae6103','1abad4cb-b886-40c1-917e-372c44ebbe05',1,5000,'2024-01-28 16:29:31',NULL),
('6d31c2d0-ba04-4d77-86ab-dafc1abc0ecc','f85a8d50-8a60-471c-b593-e9c582c3ad24','1abad4cb-b886-40c1-917e-372c44ebbe05',1,5000,'2024-01-28 16:30:34',NULL),
('760cd32b-3e20-4dd3-88ac-a502fa707e90','9542e90f-6f11-4682-9502-1e25facde6f8','1abad4cb-b886-40c1-917e-372c44ebbe05',1,5000,'2024-01-28 16:44:54',NULL),
('885da57c-3e3e-41ac-9cc8-dd638f089ed7','5540edd8-de9d-42c2-ac3e-fc0fec3c259d','1abad4cb-b886-40c1-917e-372c44ebbe05',1,5000,'2024-01-28 16:45:02',NULL),
('95e10e4c-aa69-42b9-9a0e-6df2467bde58','7c044a6e-de8a-400c-9b21-891d542d3793','1abad4cb-b886-40c1-917e-372c44ebbe05',1,5000,'2024-01-28 16:16:23',NULL),
('b16e8628-b8a7-482d-a7a6-e074b4f22a4d','c4904ba1-0792-4ea2-8949-4176988cde76','1abad4cb-b886-40c1-917e-372c44ebbe05',1,5000,'2024-01-28 15:05:37',NULL),
('be42ffe7-ad7d-463a-a952-fd81073bb131','57800151-25fa-4080-b71e-7661131e64f2','1abad4cb-b886-40c1-917e-372c44ebbe05',1,5000,'2024-01-28 16:40:25',NULL),
('eb1e2701-48bc-4e33-8962-ef1bcd97f40b','de824e3b-c44a-491f-8a6b-8faa9df9658d','1abad4cb-b886-40c1-917e-372c44ebbe05',1,5000,'2024-01-28 16:11:59',NULL);

/*Table structure for table `tbl_product` */

DROP TABLE IF EXISTS `tbl_product`;

CREATE TABLE `tbl_product` (
  `id_product` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id_product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `tbl_product` */

insert  into `tbl_product`(`id_product`,`name`,`price`,`qty`,`created_at`,`updated_at`) values 
('1748ab7f-5350-4e0b-9ef8-c77a9ebb81f2','Kopi ABC Susu',4000,0,'2024-01-25 15:13:50',NULL),
('1abad4cb-b886-40c1-917e-372c44ebbe05','Kopi Luwak',5000,10,'2024-01-24 10:23:29',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
