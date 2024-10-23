package jumbox;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Jumbox {

	public static void main(String[] args) {
		conexion.getInstance().getConnection();

        boolean salir = false;
        Usuario usuarioActual = null;

        while (!salir && usuarioActual == null) { // Solo continuar si no hay usuario logueado
            String[] opciones = { "Iniciar Sesión", "Registrarse", "Salir" };
            String opcion = (String) JOptionPane.showInputDialog(null, "Seleccione una opción", "Menú principal",
                    JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            if (opcion == null || opcion.equals("Salir")) {
                salir = true;
                JOptionPane.showMessageDialog(null, "Gracias por usar Jumbox. ¡Hasta luego!");
                continue;
            }

            switch (opcion) {
                case "Iniciar Sesión":
                    String email = JOptionPane.showInputDialog(null, "Ingrese su email:");
                    String contrasena = JOptionPane.showInputDialog(null, "Ingrese su contraseña:");

                    usuarioActual = Usuario.iniciarSesion(email, contrasena);
                    if (usuarioActual != null) {
                        JOptionPane.showMessageDialog(null, "Bienvenido " + usuarioActual.getNombre());
                        break; // Romper el bucle si se inicia sesión correctamente
                    } else {
                        JOptionPane.showMessageDialog(null, "Credenciales incorrectas. Intente nuevamente.");
                    }
                    break;

                case "Registrarse":
                    Usuario.registrarUsuario();
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }

        // Segundo bucle para acciones después de iniciar sesión
        while (!salir) {
            LinkedList<String> opcionesList = new LinkedList<>();
            opcionesList.add("Agregar producto");
            opcionesList.add("Ver productos");

            if (usuarioActual instanceof Administrador || usuarioActual instanceof EncargadoDeposito) {
                opcionesList.add("Modificar stock de producto");
            }
            if (usuarioActual instanceof Administrador) {
                opcionesList.add("Eliminar producto");
            }
            opcionesList.add("Salir");

            String[] opciones = opcionesList.toArray(new String[0]);

            String opcion = (String) JOptionPane.showInputDialog(null, "Seleccione una acción", "Menú",
                    JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            if (opcion == null || opcion.equals("Salir")) {
                salir = true;
                continue;
            }

            switch (opcion) {
                case "Agregar producto":
                    String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto:");
                    String[] categorias = { "Electro", "Hogar y Textil", "Tiempo Libre", "Bebés y Niños", "Almacén",
                            "Bebidas", "Frutas y Verduras", "Carnes", "Pescados y Mariscos", "Quesos y Fiambres",
                            "Lácteos", "Congelados", "Panadería y Repostería", "Comidas Preparadas", "Perfumería",
                            "Limpieza", "Mascotas" };

                    String categoria = (String) JOptionPane.showInputDialog(null,
                            "Seleccione la categoría del producto:", "Categorías", JOptionPane.PLAIN_MESSAGE, null,
                            categorias, categorias[0]);
                    double precio = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el precio del producto:"));
                    int stock = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el stock del producto:"));
                    String ubicacion = JOptionPane.showInputDialog(null, "Ingrese la ubicación del producto:");
                    LocalDate fechaVto = null;
                    boolean fechaValida = false;

                    while (!fechaValida) {
                        try {
                            fechaVto = LocalDate.parse(JOptionPane.showInputDialog(null,
                                    "Ingrese la fecha de vencimiento (YYYY-MM-DD):"));
                            if (fechaVto.isBefore(LocalDate.now())) {
                                JOptionPane.showMessageDialog(null,
                                        "La fecha de vencimiento no puede anterior al día actual ya que estaría vencido el producto. Inténtelo de nuevo.");
                            } else {
                                fechaValida = true;
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null,
                                    "Formato de fecha incorrecto. Por favor, use el formato correcto con sus guiones incluidos YYYY-MM-DD.");
                        }
                    }

                    Producto nuevoProducto = new Producto(nombre, categoria, precio, fechaVto, stock, ubicacion);
                    Descuento descuento = new Descuento(50, LocalDate.now(), fechaVto) {
                        @Override
                        public boolean aplicarDescuento(Producto producto) {
                            return super.aplicarDescuento(producto);
                        }
                    };

                    if (descuento.aplicarDescuento(nuevoProducto)) {
                        JOptionPane.showMessageDialog(null,
                                "Se aplicó un descuento del 50% ya que el producto: " + nuevoProducto.getNombre()
                                        + " vencerá en los próximos dos meses. \nNuevo precio: $" + nuevoProducto.getPrecio());
                    }
                    usuarioActual.agregarProducto(nuevoProducto);

                    Notificacion notificacion = new Notificacion(LocalDate.now(), "Stock bajo", "Pendiente");
                    notificacion.generarNotificacionStockBajo(nuevoProducto);

                    break;

                case "Ver productos":
                    usuarioActual.verProductos();
                    break;

                case "Modificar stock de producto":
                    if (usuarioActual instanceof Administrador || usuarioActual instanceof EncargadoDeposito) {
                        String prodModificar = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto para modificar:");
                        Producto productoModificado = usuarioActual.buscarProductoPorNombre(prodModificar);
                        if (productoModificado != null) {
                            int nuevoStock = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el nuevo stock:"));
                            usuarioActual.modificarProducto(productoModificado, nuevoStock);
                            Notificacion notificacion2 = new Notificacion(LocalDate.now(), "Stock bajo", "Pendiente");
                            if (nuevoStock < 100) {
                                notificacion2.generarNotificacionStockBajo(productoModificado);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Operación no permitida.");
                    }
                    break;

                case "Eliminar producto":
                    if (usuarioActual instanceof Administrador) {
                        String productoEliminar = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto a eliminar:");
                        usuarioActual.eliminarProducto(productoEliminar);
                    } else {
                        JOptionPane.showMessageDialog(null, "Operación no permitida.");
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }
}