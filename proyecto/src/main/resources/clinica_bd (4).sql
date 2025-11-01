-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-11-2025 a las 04:35:33
-- Versión del servidor: 10.1.37-MariaDB
-- Versión de PHP: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `clinica_bd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados`
--

CREATE TABLE `estados` (
  `id_estado` int(11) NOT NULL,
  `nombre_estado` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `estados`
--

INSERT INTO `estados` (`id_estado`, `nombre_estado`) VALUES
(1, 'Disponible'),
(2, 'Reservado'),
(3, 'Atendido'),
(4, 'Cancelado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historia_clinica`
--

CREATE TABLE `historia_clinica` (
  `id_historiaclinica` int(11) NOT NULL,
  `dni_paciente` int(11) NOT NULL,
  `antecedentes` varchar(150) NOT NULL,
  `alergias` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `historia_clinica`
--

INSERT INTO `historia_clinica` (`id_historiaclinica`, `dni_paciente`, `antecedentes`, `alergias`) VALUES
(1, 40123456, 'Hipertensión controlada', 'Ninguna'),
(2, 40234567, 'Sin antecedentes relevantes', 'Penicilina'),
(3, 40345678, 'Diabetes tipo 2', 'Ninguna'),
(4, 40456789, 'Asma leve', 'Ibuprofeno'),
(5, 40567890, 'Migrañas ocasionales', 'Ninguna'),
(7, 44555999, 'No registrados', 'No registradas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medico`
--

CREATE TABLE `medico` (
  `dni_medico` int(11) NOT NULL,
  `matricula` varchar(15) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `especialidad` varchar(100) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `email` varchar(150) NOT NULL,
  `dias_atencion` varchar(100) NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `medico`
--

INSERT INTO `medico` (`dni_medico`, `matricula`, `nombre`, `apellido`, `especialidad`, `telefono`, `email`, `dias_atencion`, `hora_inicio`, `hora_fin`) VALUES
(30111222, 'MP12345', 'Sofía', 'López', 'Clínica Médica', '2954567890', 'sofia.lopez@hospital.com', 'Lunes, Miércoles, Viernes', '08:00:00', '12:00:00'),
(30222333, 'MP54321', 'Martín', 'Ramos', 'Pediatría', '2954765123', 'martin.ramos@hospital.com', 'Martes, Jueves', '09:00:00', '13:00:00'),
(30333444, 'MP11223', 'Laura', 'Benítez', 'Cardiología', '2954987543', 'laura.benitez@hospital.com', 'Lunes, Martes, Jueves', '10:00:00', '14:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `dni_paciente` int(11) NOT NULL,
  `nombre` varchar(500) NOT NULL,
  `apellido` varchar(500) NOT NULL,
  `obra_social` varchar(500) NOT NULL,
  `fecha_nacimiento` datetime NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `email` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`dni_paciente`, `nombre`, `apellido`, `obra_social`, `fecha_nacimiento`, `telefono`, `email`) VALUES
(40123456, 'María', 'Gómez', 'OSDE', '1990-05-10 00:00:00', '2954123456', 'maria.gomez@mail.com'),
(40234567, 'Lucas', 'Fernández', 'Swiss Medical', '1988-11-23 00:00:00', '2954678912', 'lucas.fernandez@mail.com'),
(40345678, 'Ana', 'Ruiz', 'PAMI', '1955-02-17 00:00:00', '2954987654', 'ana.ruiz@mail.com'),
(40456789, 'Carlos', 'Pereyra', 'OSDE', '1979-09-30 00:00:00', '2954765432', 'carlos.pereyra@mail.com'),
(40567890, 'Julieta', 'Martínez', 'IOMA', '1995-07-15 00:00:00', '2954234987', 'julieta.martinez@mail.com'),
(44555999, 'Mercedes', 'López', 'PAMI', '1960-05-10 00:00:00', '2954656565', 'mercedes.lopez@mail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro_medico`
--

CREATE TABLE `registro_medico` (
  `id_registromedico` int(11) NOT NULL,
  `id_turno` int(11) NOT NULL,
  `id_medico` int(11) NOT NULL,
  `id_historiaclinica` int(11) NOT NULL,
  `diagnostico` varchar(150) NOT NULL,
  `tratamiento` varchar(150) NOT NULL,
  `estudios` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `registro_medico`
--

INSERT INTO `registro_medico` (`id_registromedico`, `id_turno`, `id_medico`, `id_historiaclinica`, `diagnostico`, `tratamiento`, `estudios`) VALUES
(1, 1, 30111222, 1, 'Presión arterial controlada', 'Continuar con dieta y medicación', 'Análisis de sangre'),
(2, 3, 30222333, 3, 'Cuadro viral leve', 'Reposo e hidratación', 'Ninguno'),
(3, 4, 30333444, 4, 'Arritmia leve detectada', 'Evaluación con ECG cada 6 meses', 'Electrocardiograma');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turno`
--

CREATE TABLE `turno` (
  `id_turno` int(11) NOT NULL,
  `dni_paciente` int(11) NOT NULL,
  `dni_medico` int(11) NOT NULL,
  `fecha_turno` datetime NOT NULL,
  `hora_turno` time NOT NULL,
  `id_estado` int(11) NOT NULL,
  `motivo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `turno`
--

INSERT INTO `turno` (`id_turno`, `dni_paciente`, `dni_medico`, `fecha_turno`, `hora_turno`, `id_estado`, `motivo`) VALUES
(1, 40123456, 30111222, '2025-10-22 08:30:00', '08:30:00', 2, 'Control general'),
(2, 40234567, 30111222, '2025-10-23 09:00:00', '09:00:00', 1, 'Chequeo anual'),
(3, 40345678, 30222333, '2025-10-24 09:30:00', '09:30:00', 2, 'Consulta pediátrica'),
(4, 40456789, 30333444, '2025-10-27 10:15:00', '10:15:00', 3, 'Evaluación cardiológica'),
(5, 40567890, 30111222, '2025-10-25 11:00:00', '11:00:00', 1, 'Dolor de cabeza'),
(7, 40123456, 30111222, '2025-10-22 09:30:00', '09:30:00', 1, 'electrocardiograma');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `estados`
--
ALTER TABLE `estados`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indices de la tabla `historia_clinica`
--
ALTER TABLE `historia_clinica`
  ADD PRIMARY KEY (`id_historiaclinica`),
  ADD KEY `fk_historia_paciente` (`dni_paciente`);

--
-- Indices de la tabla `medico`
--
ALTER TABLE `medico`
  ADD PRIMARY KEY (`dni_medico`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`dni_paciente`);

--
-- Indices de la tabla `registro_medico`
--
ALTER TABLE `registro_medico`
  ADD PRIMARY KEY (`id_registromedico`),
  ADD KEY `fk_consulta_turno` (`id_turno`),
  ADD KEY `fk_consulta_medico` (`id_medico`),
  ADD KEY `fk_consulta_historia` (`id_historiaclinica`);

--
-- Indices de la tabla `turno`
--
ALTER TABLE `turno`
  ADD PRIMARY KEY (`id_turno`),
  ADD KEY `dni_paciente` (`dni_paciente`),
  ADD KEY `dni_medico` (`dni_medico`),
  ADD KEY `id_estado` (`id_estado`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `estados`
--
ALTER TABLE `estados`
  MODIFY `id_estado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `historia_clinica`
--
ALTER TABLE `historia_clinica`
  MODIFY `id_historiaclinica` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `registro_medico`
--
ALTER TABLE `registro_medico`
  MODIFY `id_registromedico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `turno`
--
ALTER TABLE `turno`
  MODIFY `id_turno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `historia_clinica`
--
ALTER TABLE `historia_clinica`
  ADD CONSTRAINT `fk_historia_paciente` FOREIGN KEY (`dni_paciente`) REFERENCES `paciente` (`dni_paciente`);

--
-- Filtros para la tabla `registro_medico`
--
ALTER TABLE `registro_medico`
  ADD CONSTRAINT `fk_consulta_historia` FOREIGN KEY (`id_historiaclinica`) REFERENCES `historia_clinica` (`id_historiaclinica`),
  ADD CONSTRAINT `fk_consulta_medico` FOREIGN KEY (`id_medico`) REFERENCES `medico` (`dni_medico`),
  ADD CONSTRAINT `fk_consulta_turno` FOREIGN KEY (`id_turno`) REFERENCES `turno` (`id_turno`);

--
-- Filtros para la tabla `turno`
--
ALTER TABLE `turno`
  ADD CONSTRAINT `turno_ibfk_1` FOREIGN KEY (`dni_paciente`) REFERENCES `paciente` (`dni_paciente`),
  ADD CONSTRAINT `turno_ibfk_2` FOREIGN KEY (`dni_medico`) REFERENCES `medico` (`dni_medico`),
  ADD CONSTRAINT `turno_ibfk_3` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
