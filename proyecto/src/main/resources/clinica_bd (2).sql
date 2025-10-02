-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-10-2025 a las 04:08:28
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
-- Estructura de tabla para la tabla `agenda`
--

CREATE TABLE `agenda` (
  `id_agenda` int(11) NOT NULL,
  `dni_medico` int(11) NOT NULL,
  `fecha_atencion` datetime NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  `estado_agenda` varchar(150) NOT NULL,
  `descripcion` varchar(150) NOT NULL,
  `ubicacion` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `agenda`
--

INSERT INTO `agenda` (`id_agenda`, `dni_medico`, `fecha_atencion`, `hora_inicio`, `hora_fin`, `estado_agenda`, `descripcion`, `ubicacion`) VALUES
(1, 27777111, '2025-10-01 00:00:00', '08:00:00', '12:00:00', 'Disponible', 'Consultas generales', 'Consultorio 1'),
(2, 28999333, '2025-10-01 00:00:00', '09:00:00', '13:00:00', 'Disponible', 'Control de pacientes cardiacos', 'Consultorio 2'),
(3, 29888555, '2025-10-02 00:00:00', '09:00:00', '12:00:00', 'Disponible', 'Control ginecologico', 'Consultorio 3'),
(4, 30111222, '2025-10-04 00:00:00', '08:00:00', '12:00:00', 'Disponible', 'Seguimiento pediatria', 'Consultorio 4'),
(5, 31222444, '2025-10-05 00:00:00', '10:00:00', '14:00:00', 'Disponible', 'Tratamiento dermatologia', 'Consultorio 5'),
(6, 27777111, '2025-10-03 00:00:00', '09:00:00', '12:00:00', 'Disponible', 'Consultas atencion clinica', 'Consultorio 1'),
(7, 27777111, '2025-10-04 00:00:00', '09:00:00', '13:00:00', 'Disponible', 'Consultas clinica medica', 'Consultorio 1'),
(8, 27777111, '2025-10-05 00:00:00', '09:00:00', '11:00:00', 'Disponible', 'Consultas clinica medica', 'Consultorio 1'),
(9, 31222444, '2025-10-06 00:00:00', '10:00:00', '14:00:00', 'Disponible', 'Revision de piel y dermatosis', 'Consultorio 5'),
(10, 31222444, '2025-10-07 00:00:00', '10:00:00', '14:00:00', 'Disponible', 'Tratamiento dermatologia', 'Consultorio 5'),
(11, 31222444, '2025-10-08 00:00:00', '09:00:00', '14:00:00', 'Disponible', 'Revision de piel y dermatosis', 'Consultorio 5');

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
(1, 'Reservado'),
(2, 'Confirmado'),
(3, 'Finalizado'),
(4, 'Cancelado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historia_clinica`
--

CREATE TABLE `historia_clinica` (
  `id_historiaclinica` int(11) NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  `alergia` varchar(150) NOT NULL,
  `antecedente` varchar(150) NOT NULL,
  `medicacion_actual` varchar(150) NOT NULL,
  `observacion` varchar(150) NOT NULL,
  `ultima_actualizacion` datetime NOT NULL,
  `dni_paciente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `historia_clinica`
--

INSERT INTO `historia_clinica` (`id_historiaclinica`, `fecha_creacion`, `alergia`, `antecedente`, `medicacion_actual`, `observacion`, `ultima_actualizacion`, `dni_paciente`) VALUES
(1, '2000-10-01 00:00:00', 'penicilina', 'hipertension leve', 'ibuprofeno', 'paciente refiere dolor de cabeza frecuente', '2025-08-01 00:00:00', 1),
(2, '2001-10-01 00:00:00', 'ninguna', 'sin antecedentes relevantes', 'paracetamol', 'consulta por dolor abdominal', '2025-07-01 00:00:00', 2),
(3, '2005-07-20 00:00:00', 'polen', 'asma', 'loratadina', 'paciente estable, continuar medicacion', '2025-02-22 00:00:00', 3),
(4, '2000-07-24 00:00:00', 'mariscos', 'gastritis cronica', 'omeprazol', 'se recomienda dieta balanceada', '2025-04-24 00:00:00', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medico`
--

CREATE TABLE `medico` (
  `dni_medico` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `especialidad` varchar(100) NOT NULL,
  `telefono` int(11) NOT NULL,
  `email` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `medico`
--

INSERT INTO `medico` (`dni_medico`, `nombre`, `apellido`, `especialidad`, `telefono`, `email`) VALUES
(27777111, 'Ricardo', 'Lopez', 'Clinica Medica', 1155667788, 'ricardo.lopez@clinica.com'),
(28999333, 'Jose', 'Fernandez', 'Cardiologia', 1133445566, 'jose.fernandez@clinica.com'),
(29888555, 'Sofia', 'Torres', 'Ginecologia', 1166778899, 'sofia.torres@clinica.com'),
(30111222, 'Laura', 'Martinez', 'Pediatria', 1122334455, 'laura.martinez@clinica.com'),
(31222444, 'Mariana', 'Gomez', 'Dermatologia', 1144556677, 'mariana.gomez@clinica.com');

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
  `telefono` int(11) NOT NULL,
  `email` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`dni_paciente`, `nombre`, `apellido`, `obra_social`, `fecha_nacimiento`, `telefono`, `email`) VALUES
(1, 'Juan', 'Perez', 'OSDE', '1995-04-12 00:00:00', 1123456789, 'juan.perez@gmail.com'),
(2, 'Maria', 'Lopez', 'Swiss Med', '1992-11-30 00:00:00', 1145678910, 'maria.lopez@hotmail.com'),
(3, 'Carlos', 'Gomez', 'Galeno', '1978-07-05 00:00:00', 1133345566, 'carlos.gomez@yahoo.com'),
(4, 'Luis', 'Torres', 'PAMI', '1965-09-18 00:00:00', 1199988776, 'luis.torres@hotmail.com');

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
(1, 1, 27777111, 1, 'hipertensión leve', 'control mensual, dieta', 'tension arterial, ECG'),
(2, 2, 27777111, 2, 'gripe comun', 'reposo, paracetamol, liquidos', 'temperatura, examen respiratorio'),
(3, 5, 31222444, 3, 'manchas hiperpigmentadas', 'protector solar, crema despigmentante tópica', 'fotografia clinica, dermatoscopia'),
(4, 6, 31222444, 4, 'acne leve', 'crema hidratante, higiene facial diaria', 'examen fisico dermatologico');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turno`
--

CREATE TABLE `turno` (
  `id_turno` int(11) NOT NULL,
  `id_agenda` int(11) NOT NULL,
  `dni_paciente` int(11) NOT NULL,
  `fecha_turno` datetime NOT NULL,
  `hora_turno` time NOT NULL,
  `id_estado` int(11) NOT NULL,
  `motivo` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `turno`
--

INSERT INTO `turno` (`id_turno`, `id_agenda`, `dni_paciente`, `fecha_turno`, `hora_turno`, `id_estado`, `motivo`) VALUES
(1, 1, 1, '2025-10-01 00:00:00', '08:00:00', 1, 'control de presion'),
(2, 1, 2, '2025-10-01 00:00:00', '09:00:00', 1, 'consulta por fiebre'),
(3, 1, 3, '2025-10-01 00:00:00', '10:00:00', 1, 'dolor en el pie'),
(4, 4, 2, '2025-10-02 00:00:00', '08:00:00', 1, 'fiebre y tos'),
(5, 5, 3, '2025-10-05 00:00:00', '10:00:00', 1, 'manchas en la piel'),
(6, 5, 4, '2025-10-05 00:00:00', '11:00:00', 1, 'tratamiento acne');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `agenda`
--
ALTER TABLE `agenda`
  ADD PRIMARY KEY (`id_agenda`),
  ADD KEY `fk_agenda_medico` (`dni_medico`);

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
  ADD KEY `fk_turno_paciente` (`dni_paciente`),
  ADD KEY `fk_turno_estado` (`id_estado`),
  ADD KEY `fk_turno_agenda` (`id_agenda`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `agenda`
--
ALTER TABLE `agenda`
  MODIFY `id_agenda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `estados`
--
ALTER TABLE `estados`
  MODIFY `id_estado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `historia_clinica`
--
ALTER TABLE `historia_clinica`
  MODIFY `id_historiaclinica` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `registro_medico`
--
ALTER TABLE `registro_medico`
  MODIFY `id_registromedico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `turno`
--
ALTER TABLE `turno`
  MODIFY `id_turno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `agenda`
--
ALTER TABLE `agenda`
  ADD CONSTRAINT `fk_agenda_medico` FOREIGN KEY (`dni_medico`) REFERENCES `medico` (`dni_medico`) ON DELETE CASCADE ON UPDATE CASCADE;

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
  ADD CONSTRAINT `fk_turno_agenda` FOREIGN KEY (`id_agenda`) REFERENCES `agenda` (`id_agenda`),
  ADD CONSTRAINT `fk_turno_estado` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`),
  ADD CONSTRAINT `fk_turno_paciente` FOREIGN KEY (`dni_paciente`) REFERENCES `paciente` (`dni_paciente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
