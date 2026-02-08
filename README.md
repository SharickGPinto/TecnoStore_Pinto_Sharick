<h1 align=center>Java TecnoStore</h1>
 

 <h6 align=center>Proyecto Java: (Sharick Giovanna Pinto Rodriguez)</h6>

 <div align="center">

<img src="https://img.shields.io/badge/Java-17+-ED8B00?logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/MySQL-8.0+-4479A1?logo=mysql&logoColor=white"/>
<img src="https://img.shields.io/badge/JDBC-Driver-blue?logo=java&logoColor=white"/>

</div>

---

# Tabla de Contenido

<h6 align=center> 1. Introducción </h6> 
<h6 align=center> 2. Caso de Estudio </h6> 
<h6 align=center> 3. Descripción del proyecto </h6>
<h6 align=center> 4. Requisitos Funcionales y No Funcionales </h6>
<h6 align=center> 5. Modelo de Clases (POO) </h6> 
<h6 align=center> 6. Diseño de Persistencia (MySQL + JDBC) </h6>
<h6 align=center> 7. Estructura de la Base de Datos </h6> 
<h6 align=center> 8. Implementación de Colecciones y Stream API </h6> 
<h6 align=center> 9. Validaciones y Manejo de Excepciones </h6>
<h6 align=center> 10. Patrones de Diseño y Principios SOLID </h6>
<h6 align=center> 11. Ejemplos de Ejecución en Consola </h6>
<h6 align=center> 12. Reportes Generados (TXT y consultas) </h6> 
<h6 align=center> 13. Cierre y Resultados </h6>

---


---
# Introducción

TecnoStore es una tienda que vende celulares de diferentes marcas y gamas, pero el problema es que todo lo están llevando a mano en hojas de cálculo, y eso termina siendo un desorden: se repite información, se cometen errores y es difícil tener control real del inventario y las ventas. Por eso nace la idea de hacer un sistema en consola con Java que permita manejar de forma más organizada el catálogo de celulares, los clientes y las ventas, y así automatizar lo que antes se hacía “a la mala”.

En este proyecto se va a documentar todo el proceso que se siguió para construir el sistema: desde cómo se organizaron las clases (modelo, controladores, utilidades, etc.), hasta cómo se aplicaron cosas importantes como POO, colecciones, manejo de excepciones, Stream API y un patrón de diseño (por ejemplo Strategy). La idea es que no sea solo “un programa que corre”, sino un sistema que tenga lógica clara y que se pueda entender y mantener.

También se explica cómo se manejan las reglas clave del sistema, como validar datos (por ejemplo: que el precio y stock sean positivos, que la identificación del cliente no se repita, y que el correo tenga un formato válido), además del cálculo del total de la venta incluyendo el IVA del 19% y la actualización del stock cuando se vende un celular.

Finalmente, se muestran pruebas y ejemplos de ejecución en consola, y se deja lista la parte de conexión con MySQL usando JDBC para guardar las ventas y su detalle. Además, se incluye la generación del archivo reporte_ventas.txt, que resume todas las ventas realizadas, junto con reportes usando Stream API como celulares con stock bajo, top de vendidos y ventas por mes.


---


# 2. Caso de Estudio

En este caso, TecnoStore tiene un problema súper típico: están manejando ventas, clientes e inventario en hojas de cálculo, todo manual, y eso se vuelve un caos. Se termina repitiendo información, se cometen errores (porque obvio, todo lo hace una persona a mano), y cuando necesitan saber algo básico como cuánto stock queda o qué se vendió más, toca ponerse a revisar tablas y eso no es confiable ni rápido.

La tienda necesita un sistema más serio y centralizado que les permita llevar el control real del negocio: registrar celulares con sus datos completos, guardar clientes de forma correcta (sin duplicados), y registrar ventas calculando el total con IVA del 19%, además de ir descontando el stock automáticamente. La idea no es solo “guardar datos”, sino que el sistema tenga reglas claras para que no se metan datos mal (por ejemplo: precios negativos, stock raro, correos inválidos o clientes repetidos).

---

# 3. Descripción del proyecto

Este proyecto tiene como objetivo diseñar y desarrollar un sistema para TecnoStore, una tienda de celulares que necesita dejar atrás el manejo manual en hojas de cálculo. La idea es construir una solución en Java (consola), apoyada por MySQL, que permita llevar un control más organizado, consistente y seguro de la información, evitando errores típicos como datos repetidos, registros incompletos o inventario mal calculado.

# 4. Requisitos Funcionales y No Funcionales

## Requisitos Funcionales

CRUD de Celulares: registrar, listar, buscar por ID, actualizar y eliminar.
Validaciones de Celulares: precio > 0 y stock ≥ 0.

CRUD de Clientes: registrar, listar, buscar (por identificación), actualizar y eliminar.

Validaciones de Clientes: identificación única y correo con formato válido.
Gestión de Ventas:
registrar una venta seleccionando un cliente y uno o varios celulares (con cantidad).

calcular subtotal, IVA (19%) y total final.
descontar stock automáticamente según lo vendido.

guardar la venta y su detalle en MySQL con JDBC.

Reportes en consola (usando Stream API):
celulares con stock bajo (menor a 5).
Top 3 celulares más vendidos.
ventas totales por mes.
Archivo de salida:
generar reporte_ventas.txt con el resumen de las ventas realizadas.

---

## Requisitos No Funcionales

Aplicación de consola en Java, con menús claros y flujo entendible.

POO real: encapsulamiento + herencia + composición (ej: Venta→Items, Cliente→Persona).

Principios SOLID (al menos lo básico bien aplicado: separar responsabilidades y no mezclar todo).

Patrón de diseño: implementar Strategy (descuentos) o el patrón que el proyecto permita.

Manejo de excepciones y uso de try-with-resources (archivos y JDBC).
Integridad de datos: evitar duplicados, valores inválidos y ventas sin stock suficiente.

Código mantenible: evitar código espagueti separando modelo / controlador / persistencia / util.

Si quieres, te lo dejo con el mismo formato de tu documento (títulos numerados y estilo más “formal”), pero esto ya está 100% adaptado a TecnoStore y a lo que te piden.
