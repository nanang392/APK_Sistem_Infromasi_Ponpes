-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 18 Jan 2025 pada 07.22
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `siponpes`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `absensi`
--

CREATE TABLE `absensi` (
  `no` varchar(11) NOT NULL,
  `semester` varchar(20) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `nisn` varchar(20) NOT NULL,
  `kelas` varchar(20) NOT NULL,
  `kehadiran` varchar(20) NOT NULL,
  `alfa` varchar(50) NOT NULL,
  `izin` varchar(50) NOT NULL,
  `sakit` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `absensi`
--

INSERT INTO `absensi` (`no`, `semester`, `nama`, `nisn`, `kelas`, `kehadiran`, `alfa`, `izin`, `sakit`) VALUES
('1', 'GANJIL', 'M Rohman Nur Fawahid', '136485243', 'KELAS 7', '119 Pertemuan', '0', '2', '2'),
('2', 'GANJIL ', 'Ardena Pramesta', '3102995169', 'KELAS 7', '119 Pertemuan', '0', '2', '2'),
('3', 'GANJIL ', 'Ferdiyansyah', '3088884225', 'KELAS 7', '119 Pertemuan', '0', '2', '3');

-- --------------------------------------------------------

--
-- Struktur dari tabel `gedung`
--

CREATE TABLE `gedung` (
  `no` int(11) NOT NULL,
  `namagedung` varchar(50) NOT NULL,
  `kondisi` varchar(20) NOT NULL,
  `kepemilikan` varchar(20) NOT NULL,
  `jmllantai` int(30) NOT NULL,
  `tahundibangun` int(20) NOT NULL,
  `luas` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `gedung`
--

INSERT INTO `gedung` (`no`, `namagedung`, `kondisi`, `kepemilikan`, `jmllantai`, `tahundibangun`, `luas`) VALUES
(1, 'Gedung Utama', 'Baik', ' MILIK SENDIRI', 2, 2009, 1000),
(2, 'Gedung Kantor', ' Baik', ' MILIK SENDIRI', 2, 2009, 1100),
(3, 'GEDUNG ASRAMA', ' Baik', ' MILIK SENDIRI', 2, 2014, 600),
(4, 'GEDUNG KELAS', ' Rusak Ringan', 'MILIK SENDIRI', 1, 2014, 900),
(5, 'Aula Utama', 'Baik', 'MILIK SENDIRI', 1, 2023, 50);

-- --------------------------------------------------------

--
-- Struktur dari tabel `jadwal`
--

CREATE TABLE `jadwal` (
  `no` int(11) NOT NULL,
  `hari` varchar(20) NOT NULL,
  `jam` varchar(10) NOT NULL,
  `kelas` varchar(20) NOT NULL,
  `mapel` varchar(20) NOT NULL,
  `pengajar` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `jadwal`
--

INSERT INTO `jadwal` (`no`, `hari`, `jam`, `kelas`, `mapel`, `pengajar`) VALUES
(1, 'SENIN', '08 : 00', 'Kelas 7', 'MTK', 'Tiara, S.Pd'),
(2, 'SENIN', '10 : 00', 'Kelas 7', 'Hadis', 'TOIPAH, S.pd'),
(3, 'SENIN', '11 : 30', 'Kelas 7', 'B.Arab', 'Kandias'),
(4, 'SENIN', '08 : 00', 'Kelas 8', 'Nahwu', 'Akhmad Akbar G'),
(5, 'SENIN', '10 : 00', 'Kelas 8', 'MTK', 'Tiara, S.Pd'),
(6, 'SENIN', '11 : 30', 'Kelas 8', 'Hadis', 'Akhmad Akbar G'),
(7, 'SENIN', '08 : 00', 'Kelas 9', 'Hadis', 'Akhmad Akbar G'),
(8, 'SENIN', '10 : 00', 'Kelas 9', 'Al-Quran', 'KHASANURI, MA'),
(9, 'SENIN', '11 : 30', 'Kelas 9', 'MTK', 'Tiara, S.Pd'),
(10, 'SELASA', '08 : 00', 'Kelas 7', 'Fiqh', 'Nanang Maulana '),
(11, 'SELASA', '10 : 00', 'Kelas 7', 'IPA', 'Tiara, S.Pd'),
(12, 'SELASA', '11 : 30', 'Kelas 7', 'Shorof', 'TOIPAH, S.pd'),
(13, 'SELASA', '08 : 00', 'Kelas 8', 'Al-Quran', 'TOIPAH, S.pd'),
(14, 'SELASA', '10 : 00', 'Kelas 8', 'Fiqh', 'Akhmad Akbar G'),
(15, 'SELASA', '11 : 30', 'Kelas 8', 'IPA', 'Tiara, S.Pd'),
(16, 'SELASA', '08 : 00', 'Kelas 9', 'Fiqh', 'Akhmad Akbar G'),
(17, 'SELASA', '10 : 00', 'Kelas 9', 'Al-Quran', 'TOIPAH, S.pd'),
(18, 'SELASA', '11 : 30', 'Kelas 9', 'IPA', 'Tiara, S.Pd'),
(19, 'RABU', '08 : 00', 'Kelas 7', 'Al-Quran', 'TOIPAH, S.pd'),
(20, 'RABU', '10 : 00', 'Kelas 7', 'Akidah', 'Akhmad Akbar G'),
(21, 'RABU', '11 : 30', 'Kelas 7', 'Fiqh', 'Nanang Maulana '),
(22, 'RABU', '08 : 00', 'Kelas 8', 'PPKN', 'Nanang Maulana '),
(23, 'RABU', '10 : 00', 'Kelas 8', 'Shorof', 'TOIPAH, S.pd');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kelas`
--

CREATE TABLE `kelas` (
  `no` int(11) NOT NULL,
  `kelas` varchar(20) NOT NULL,
  `noruangan` varchar(20) NOT NULL,
  `walikelas` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kelas`
--

INSERT INTO `kelas` (`no`, `kelas`, `noruangan`, `walikelas`) VALUES
(1, 'KELAS 7', 'R.2.1.1', 'Ibu Sri, S.Pd'),
(2, 'KELAS 8', 'R.2.1.2', 'Pak Nurhadi,S.Pd.I'),
(3, 'KELAS 10', 'R.2.1.3', 'Zaynul, S.Pd '),
(4, 'KELAS 11', 'R.2.1.4', 'Asep'),
(5, 'KELAS 9', 'R.2.2.8', 'Bu Siti, S.Pdi'),
(6, 'Kelas 12', 'R.3.2.6', 'Pak Akbar, S.Pd');

-- --------------------------------------------------------

--
-- Struktur dari tabel `nilai`
--

CREATE TABLE `nilai` (
  `no` varchar(10) NOT NULL,
  `nisn` varchar(20) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `kelas` varchar(20) NOT NULL,
  `ppkn` int(5) NOT NULL,
  `bindo` int(5) NOT NULL,
  `mtk` int(5) NOT NULL,
  `ipa` int(5) NOT NULL,
  `binggris` int(5) NOT NULL,
  `ips` int(5) NOT NULL,
  `tik` int(5) NOT NULL,
  `barab` int(5) NOT NULL,
  `alquran` int(5) NOT NULL,
  `hadist` int(5) NOT NULL,
  `fiqh` int(5) NOT NULL,
  `akhlak` int(5) NOT NULL,
  `akidah` int(5) NOT NULL,
  `tarikh` int(5) NOT NULL,
  `shorof` int(5) NOT NULL,
  `aswaja` int(5) NOT NULL,
  `total` int(10) NOT NULL,
  `nakhir` varchar(20) NOT NULL,
  `status` varchar(50) NOT NULL,
  `tketerangan` int(10) NOT NULL,
  `sakit` int(10) NOT NULL,
  `izin` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `nilai`
--

INSERT INTO `nilai` (`no`, `nisn`, `nama`, `kelas`, `ppkn`, `bindo`, `mtk`, `ipa`, `binggris`, `ips`, `tik`, `barab`, `alquran`, `hadist`, `fiqh`, `akhlak`, `akidah`, `tarikh`, `shorof`, `aswaja`, `total`, `nakhir`, `status`, `tketerangan`, `sakit`, `izin`) VALUES
('ID01', '0136485243', 'M Rohman Nur Fawahid', 'KELAS 7', 80, 78, 76, 78, 80, 80, 86, 87, 86, 85, 86, 88, 87, 86, 87, 80, 1330, '83', 'Terlampaui', 2, 1, 1),
('ID02', '3102995169', 'Ardena Pramesta', 'KELAS 7', 80, 79, 80, 78, 79, 80, 82, 75, 80, 75, 85, 80, 80, 80, 80, 75, 1268, '79.25', 'Terlampaui', 0, 2, 2),
('ID03', '3088884225', 'Ferdiyansyah', 'KELAS 7', 80, 79, 80, 78, 79, 80, 82, 75, 75, 75, 85, 75, 75, 75, 75, 75, 1243, '77.69', 'Terlampaui', 0, 2, 2),
('ID04', '3128033342', 'Achmad Mujaki', 'KELAS 7', 80, 79, 80, 79, 79, 80, 82, 80, 88, 90, 85, 85, 85, 85, 87, 86, 1330, '83.13', 'Terlampaui', 0, 2, 2),
('ID05', '3105124493', 'M Zainul Mustapa', 'KELAS 8', 80, 80, 80, 80, 78, 80, 85, 75, 85, 85, 80, 85, 80, 85, 80, 88, 1306, '81.63', 'Terlampaui', 2, 2, 2),
('ID06', '0109579744', 'Averusy Nurul Hakim', 'KELAS 9', 85, 80, 78, 78, 79, 85, 84, 88, 88, 85, 86, 85, 85, 85, 85, 85, 1341, '83.81', 'Terlampaui', 0, 3, 2),
('ID07', '0099352980', 'Zulkarnain Adinegara', 'KELAS 9', 85, 80, 78, 78, 79, 85, 82, 85, 85, 85, 80, 85, 80, 85, 78, 80, 1310, '81.88', 'Terlampaui', 0, 3, 2),
('ID08', '0092092689', 'Salman Al Fatih', 'KELAS 9', 85, 80, 78, 78, 79, 80, 83, 88, 88, 85, 82, 80, 85, 85, 85, 85, 1326, '82.88', 'Terlampaui', 0, 3, 2),
('ID09', '0096027607', 'Rizky Adityo Rohman', 'KELAS 9', 85, 80, 78, 78, 79, 80, 81, 80, 80, 85, 80, 85, 80, 85, 80, 80, 1296, '81.00', 'Terlampaui', 0, 3, 2),
('ID10', '3080877878', 'Ahmad Ali', 'KELAS 10', 85, 85, 77, 85, 80, 82, 83, 80, 80, 80, 85, 80, 80, 80, 80, 80, 1302, '81.38', 'Terlampaui', 2, 2, 2),
('ID11', '3093952359', 'Fauzul Hafis', 'KELAS 10', 85, 85, 78, 85, 80, 85, 85, 90, 85, 85, 89, 80, 80, 85, 80, 87, 1344, '84.00', 'Terlampaui', 2, 2, 2),
('ID12', '0087893171', 'Muhammad Rosyid', 'KELAS 10', 85, 85, 80, 85, 80, 82, 81, 80, 80, 80, 80, 80, 80, 80, 80, 80, 1298, '81.13', 'Terlampaui', 2, 2, 2),
('ID13', '3082663680', 'Muhammad Adzkiya', 'KELAS 10', 80, 85, 81, 85, 80, 82, 84, 80, 78, 78, 75, 80, 80, 80, 80, 80, 1288, '80.50', 'Terlampaui', 2, 2, 2),
('ID14', '0081335174', 'M Dafa Khoirulloh', 'KELAS 11', 80, 75, 81, 81, 80, 82, 84, 78, 79, 80, 75, 75, 80, 78, 80, 80, 1268, '79.25', 'Terlampaui', 2, 2, 2),
('ID15', '0081515132', 'M Dafi Hibatulloh', 'KELAS 11', 80, 75, 80, 80, 80, 81, 84, 78, 78, 80, 75, 75, 80, 76, 80, 78, 1260, '78.75', 'Terlampaui', 2, 2, 2),
('ID16', '0074444443', 'Kheisan Fatih Achmad', 'KELAS 11', 85, 85, 88, 88, 80, 88, 90, 78, 85, 87, 80, 83, 80, 80, 80, 86, 1343, '83.94', 'Terlampaui', 2, 2, 2),
('ID17', '0077556121', 'Imaduddin Dai', 'KELAS 11', 80, 75, 80, 80, 80, 81, 82, 78, 75, 80, 75, 75, 80, 75, 80, 75, 1251, '78.19', 'Terlampaui', 2, 2, 2),
('ID18', '0085362308', 'M Farhan Azhary', 'KELAS 11', 80, 75, 79, 79, 80, 81, 83, 78, 80, 83, 75, 80, 80, 78, 80, 82, 1273, '79.56', 'Terlampaui', 2, 2, 2),
('ID19', '0069504519', 'A Rafiz', 'Kelas 12', 85, 85, 83, 80, 80, 80, 85, 88, 85, 85, 90, 86, 80, 85, 80, 80, 1337, '83.56', 'Terlampaui', 2, 2, 2),
('ID20', '0059406657', 'M Daffa Fahreza', 'Kelas 12', 85, 75, 76, 80, 80, 85, 83, 78, 80, 80, 78, 75, 78, 80, 80, 78, 1271, '79.44', 'Terlampaui', 2, 2, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pembayaran`
--

CREATE TABLE `pembayaran` (
  `kodetransaksi` varchar(20) NOT NULL,
  `idsantri` varchar(20) NOT NULL,
  `nisn` varchar(15) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `kelas` varchar(10) NOT NULL,
  `spp` varchar(50) NOT NULL,
  `spp2` varchar(20) NOT NULL,
  `spp3` varchar(20) NOT NULL,
  `spp4` varchar(20) NOT NULL,
  `spp5` varchar(20) NOT NULL,
  `spp6` varchar(20) NOT NULL,
  `spp7` varchar(20) NOT NULL,
  `spp8` varchar(20) NOT NULL,
  `spp9` varchar(20) NOT NULL,
  `spp10` varchar(20) NOT NULL,
  `spp11` varchar(20) NOT NULL,
  `spp12` varchar(20) NOT NULL,
  `daftarulang` varchar(50) NOT NULL,
  `ujian` varchar(50) NOT NULL,
  `total` varchar(50) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pembayaran`
--

INSERT INTO `pembayaran` (`kodetransaksi`, `idsantri`, `nisn`, `nama`, `kelas`, `spp`, `spp2`, `spp3`, `spp4`, `spp5`, `spp6`, `spp7`, `spp8`, `spp9`, `spp10`, `spp11`, `spp12`, `daftarulang`, `ujian`, `total`, `tanggal`) VALUES
('KP12024', 'ID01', '0136485243', 'M Rohman Nur Fawahid', 'KELAS 7', '250000', '250000', '250000', '250000', '250000', '250000', '0', '0', '0', '0', '0', '0', '1500000', '100000', '3100000', '2025-01-18'),
('KP22024', 'ID05', '3105124493', 'M Zainul Mustapa', 'KELAS 8', '250000', '250000', '250000', '250000', '250000', '250000', '250000', '0', '0', '0', '0', '0', '500000', '100000', '2350000', '2024-12-18'),
('KP12025', 'ID02', '3102995169', 'Ardena Pramesta', 'KELAS 7', '250000', '250000', '250000', '250000', '0', '0', '0', '0', '0', '0', '0', '0', '500000', '100000', '1600000', '2024-10-18');

-- --------------------------------------------------------

--
-- Struktur dari tabel `santri`
--

CREATE TABLE `santri` (
  `idsantri` varchar(20) NOT NULL,
  `nisn` varchar(20) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jenisk` varchar(20) NOT NULL,
  `ttl` varchar(50) NOT NULL,
  `kelas` varchar(10) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `ortu` varchar(50) NOT NULL,
  `thnmasuk` varchar(20) NOT NULL,
  `semester` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `santri`
--

INSERT INTO `santri` (`idsantri`, `nisn`, `nama`, `jenisk`, `ttl`, `kelas`, `alamat`, `ortu`, `thnmasuk`, `semester`) VALUES
('ID01', '0136485243', 'M Rohman Nur Fawahid', 'Laki - laki', 'Tanjung K, 10/01/2013', 'KELAS 7', 'JAKARTA', 'Rosiah', '2024', 'GANJIL'),
('ID02', '3102995169', 'Ardena Pramesta', 'Laki - laki', 'Gunungkidul, 25/10/2011', 'KELAS 7', 'Gunung Kidul', 'Yusuf', '2024', 'GANJIL'),
('ID03', '3088884225', 'Ferdiyansyah', 'Laki - laki', 'Sampang, 22/06/2008', 'KELAS 7', 'Bogor', 'Waryono', '2024', 'GANJIL'),
('ID04', '3128033342', 'Achmad Mujaki', 'Laki - laki', 'Brebes, 20/05/2012', 'KELAS 7', 'Jakarta', 'Daripah', '2024', 'GANJIL'),
('ID05', '3105124493', 'M Zainul Mustapa', 'Laki - laki', 'Depok, 16/10/2010', 'KELAS 8', 'DEPOK', 'Irma', '2023', 'GANJIL'),
('ID06', '0109579744', 'Averusy Nurul Hakim', 'Laki - laki', 'Jakarta, 22/05/2010', 'KELAS 9', 'Tanggerang', 'Ela Yuliana', '2022', 'GANJIL'),
('ID07', '0099352980', 'Zulkarnain Adinegara', 'Laki - laki', 'Cianjur, 31/12/2009', 'KELAS 9', 'Jakarta', 'Musannidah', '2022', 'GANJIL'),
('ID08', '0092092689', 'Salman Al Fatih', 'Laki - laki', 'Tegal, 1/02/2009', 'KELAS 9', 'Jakarta', 'Nuryati', '2022', 'GANJIL'),
('ID09', '0096027607', 'Rizky Adityo Rohman', 'Laki - laki', 'Tanggerang, 17/03/2009', 'KELAS 9', 'JAKARTA', 'Nur Rohman', '2022', 'GANJIL'),
('ID10', '3080877878', 'Ahmad Ali', 'Laki - laki', 'Jakarta, 16/07/2008', 'KELAS 10', 'JAKARTA', 'Rosyidi', '2024', 'GANJIL'),
('ID11', '3093952359', 'Fauzul Hafis', 'Laki - laki', 'Jakarta, 22/07/2009', 'KELAS 10', 'Jakarta', 'Siti Supriyati', '2024', 'GANJIL'),
('ID12', '0087893171', 'Muhammad Rosyid', 'Laki - laki', 'Jakarta, 30/03/2008', 'KELAS 10', 'Jakarta', 'Mustopa', '2024', 'GANJIL'),
('ID13', '3082663680', 'Muhammad Adzkiya', 'Laki - laki', 'Sidoarjo, 07/08/2008', 'KELAS 10', 'Bogor', 'Subardiani', '2024', 'GANJIL'),
('ID14', '0081335174', 'M Dafa Khoirulloh', 'Laki - laki', 'Bekasi, 18/06/2008', 'KELAS 11', 'Bekasi', 'Maksum', '2023', 'GANJIL'),
('ID15', '0081515132', 'M Dafi Hibatulloh', 'Laki - laki', 'Bekasi, 18/06/2008', 'KELAS 11', 'Bekasi', 'Maksum', '2023', 'GANJIL'),
('ID16', '0074444443', 'Kheisan Fatih Achmad', 'Laki - laki', 'Jakarta, 4/06/2007', 'KELAS 11', 'Jakarta', 'A Kusdiarno', '2023', 'GANJIL'),
('ID17', '0077556121', 'Imaduddin Dai', 'Laki - laki', 'Jakarta, 16/04/2007', 'KELAS 11', 'Jakarta', 'Zainuddin Dai', '2023', 'GANJIL'),
('ID18', '0085362308', 'M Farhan Azhary', 'Laki - laki', 'Jakarta, 21/01/ 2008', 'KELAS 11', 'Jakarta', 'Zaenal Arifin', '2023', 'GANJIL'),
('ID19', '0069504519', 'A Rafiz', 'Laki - laki', 'Bima, 9/09/2006', 'Kelas 12', 'Tanggerang', 'St. Aminah', '2022', 'GANJIL'),
('ID20', '0059406657', 'M Daffa Fahreza', 'Laki - laki', 'Jakarta, 25/05/2005', 'Kelas 12', 'Jakarta', 'Soemantri', '2022', 'GANJIL');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `iduser` varchar(20) NOT NULL,
  `namauser` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `level` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`iduser`, `namauser`, `username`, `password`, `level`) VALUES
('USR001', 'Admin', 'admin', '123', 'Admin'),
('USR002', 'Alif', 'Alif', '321', 'User');

-- --------------------------------------------------------

--
-- Struktur dari tabel `ustad`
--

CREATE TABLE `ustad` (
  `idustad` varchar(10) NOT NULL,
  `namaustad` varchar(50) NOT NULL,
  `jabatan` varchar(50) NOT NULL,
  `jenis` varchar(50) NOT NULL,
  `ttl` varchar(50) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `nohp` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `ustad`
--

INSERT INTO `ustad` (`idustad`, `namaustad`, `jabatan`, `jenis`, `ttl`, `alamat`, `nohp`) VALUES
('USTD01', 'KHASANURI, MA', 'Pimpinan Pondok', 'Laki - laki', '  BREBES,1979-06-15', 'Tanjung Barat, Jakarta ', '087830192245'),
('USTD02', 'Nanang Maulana ', 'Sekretaris & Pengajar', 'Laki - laki', 'BREBES, 2002-09-03', 'jl.gintung no.200', '089501343925'),
('USTD03', 'TOIPAH, S.pd', 'Pengajar', 'Perempuan', 'Brebes, 1986-04-23', 'jl.gintung', '0895383112993'),
('USTD04', 'DENY KURNIAWAN, S.Kom', 'Pengajar', 'Laki - laki', 'Jakarta, 1994-06-19', 'jl.batu merah', '082112885909'),
('USTD05', 'MUSALAL, S.pd', 'Pengajar & Humas', 'Laki - laki', '  NGAWI,1970-05-06', ' Jl.Gintung ', '082125271852'),
('USTD06', 'LUFI HENDRIYANTO, S.Kom', 'Pengajar', 'Laki - laki', 'Jakarta, 1995-09-23', 'JKP.PARUNG SERAB, Depok', ' 081311183010'),
('USTD07', 'SUGENG, S.pd', 'Pengajar', 'Laki - laki', 'PURWODADI,1970-02-07', '  JL.GINTUNG', ' 081297960950'),
('USTD08', ' AWONG WAHYUDIN, S.pd', 'Pengajar & Kebersihan', 'Laki - laki', 'CIAMIS,1972-02-29', 'Tanjung Barat, Jakarta', '081288152464'),
('USTD09  ', 'Fajar Widi Pramono, S.Pd', 'Pengajar', 'Laki - laki', 'Jakarta,2000-07-06', '  Jl. Durian Gg. H. Pondo No.26B', '0881025582221'),
('USTD10', 'Jawani,Lc', 'Bendahara & Pengajar', 'Laki - laki', 'Mempawai, 1997-02-13', 'Jakarta', '085281644171 '),
('USTD11', 'Akhmad Akbar G', 'Pengajar & Lurah Pondok', 'Laki - laki', 'Brebes,2000-5-1', 'Jakarta', '089519667314 '),
('USTD12', 'Kandias', 'Pengajar', 'Laki - laki', 'Jakarta, 2003-02-13', 'Jakarta', '085691169169'),
('USTD13', 'Tiara, S.Pd', 'Pengajar', 'Perempuan', 'Jakarta, 2000-04-17  ', '  Jakarta', '081315384495 ');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `gedung`
--
ALTER TABLE `gedung`
  ADD PRIMARY KEY (`no`);

--
-- Indeks untuk tabel `jadwal`
--
ALTER TABLE `jadwal`
  ADD PRIMARY KEY (`no`);

--
-- Indeks untuk tabel `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`no`);

--
-- Indeks untuk tabel `santri`
--
ALTER TABLE `santri`
  ADD PRIMARY KEY (`idsantri`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`iduser`);

--
-- Indeks untuk tabel `ustad`
--
ALTER TABLE `ustad`
  ADD PRIMARY KEY (`idustad`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `gedung`
--
ALTER TABLE `gedung`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `jadwal`
--
ALTER TABLE `jadwal`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT untuk tabel `kelas`
--
ALTER TABLE `kelas`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
