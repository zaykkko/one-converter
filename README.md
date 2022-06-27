
# Conversores
Conversores de una determinada moneda a otra y de temperatura (fahrenheit a celsius y viceversa).

## Solución del Challenge - Sprint 1 - ONE Java

La consigna puede visualizarse haciendo [clic aquí](https://www.aluracursos.com/challenges/oracle-one-java/sprint01-conversor-moneda) y la solución es un [archivo descargable](https://github.com/zaykkko/one-converter/releases).

**Aclaración**: es necesario descargarse monedas.csv ya que contiene información respecto a la tasa de intercambio dependiendo el país, en caso de no encontrarse dicho archivo el conversor de monedas no funcionará.

## Configurables
Se puede editar o añadir ciertas configuraciones:
- Se puede añadir o quitar países editando el [archivo csv](/monedas.csv), donde la primera columna es el código de la moneda del país en formato [ISO_4217](https://en.wikipedia.org/wiki/ISO_4217) y la segunda columna es la tasa de intercambio.
- Puedes editar (o también eliminar) el archivo [config.properties](/config.properties) donde las opciones son:

| Nombre  | Descripción de valor |
|--|--|
| `csvFileName` | _(Opcional)_ Nombre de archivo .csv que contiene listas con las distintas monedas. |
| `defaultCurrency` | _(Opcional)_ Moneda por defecto que se utilizará para las conversiones. |

