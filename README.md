🛡 Sistema de Votación
Este proyecto es un sistema de votación diseñado para gestionar y facilitar el proceso de elección de representantes estudiantiles en una institución educativa. El sistema permite a los estudiantes votar por candidatos en diferentes categorías, como representantes, personeros y contralores. Además, incluye funcionalidades para registrar administradores y jurados, así como para contabilizar y visualizar los resultados de las votaciones.

📄 Funcionalidades
1. Registro de Usuarios
Registro de Administradores: Permite registrar nuevos administradores en el sistema, quienes tienen acceso completo para gestionar y supervisar el proceso de votación.
Registro de Jurados: Permite registrar jurados que supervisan y validan el proceso de votación.
2. Autenticación
Inicio de Sesión para Administradores y Jurados: Los administradores y jurados pueden iniciar sesión para acceder a sus funcionalidades específicas y realizar sus tareas.
3. Proceso de Votación
Emisión de Votos: Los estudiantes pueden emitir sus votos por candidatos en las categorías de representante, personero y contralor. El sistema garantiza que cada estudiante pueda votar solo una vez en cada categoría.
Validación de Grado: El sistema filtra a los estudiantes para que solo puedan votar por candidatos en su mismo grado, asegurando la validez del voto. Además, se tienen en cuenta otras condiciones específicas, como la elegibilidad de los candidatos en función de los requisitos establecidos.
4. Conteo de Votos
Conteo de Votos por Representantes: Se contabilizan los votos emitidos para cada candidato a representante, ordenados por grado de manera descendente.
Conteo de Votos por Personeros: Se realiza el conteo de votos para los personeros, mostrando los resultados de manera clara y organizada.
Conteo de Votos por Contralores: El sistema cuenta y muestra los votos emitidos para los contralores.
5. Visualización de Resultados
Resultados por Categoría: Los resultados de las votaciones se pueden consultar y visualizar por categoría (representantes, personeros, contralores), permitiendo un análisis detallado y transparente de los resultados.
⚙ Tecnologías Utilizadas
Java: Lenguaje de programación principal.
Spring Boot: Framework utilizado para construir la aplicación.
MySQL: Base de datos para almacenar información de usuarios, votos y resultados.
JPA/Hibernate: Framework de mapeo objeto-relacional para la interacción con la base de datos.
Spring Security: Módulo para la autenticación y autorización en la aplicación.
React: Biblioteca para la construcción del frontend.
JWT (JSON Web Tokens): Utilizado para la autenticación y gestión de sesiones en el frontend.
🚀 Cómo Empezar
Prerrequisitos
Java 11 o superior: Asegúrate de tener una versión compatible de Java instalada.
Maven: Utilizado para la gestión de dependencias y construcción del proyecto.
MySQL: Base de datos para almacenar la información de usuarios, votos y resultados.
Node.js: Necesario para ejecutar el frontend en React.
