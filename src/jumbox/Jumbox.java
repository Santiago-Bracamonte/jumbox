package jumbox;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Jumbox {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<Usuario> usuarios = new LinkedList<>();
		
		 Administrador admin = new Administrador("Santiago", "santiago@gmail.com", "admin123");
	        Cajero cajero = new Cajero("Maria", "maria@gmail.com", "maria123");
	        EncargadoDeposito encargado = new EncargadoDeposito("Carlos", "carlos@gmail.com", "carlos123");
	        
	        // Añadir usuarios a la lista
	        usuarios.add(admin);
	        usuarios.add(cajero);
	        usuarios.add(encargado);
	        
	        Usuario usuarioActual = null;

	        String email = JOptionPane.showInputDialog(null, "Ingrese su email:");
	        String contrasena = JOptionPane.showInputDialog(null, "Ingrese su contraseña:");

	        
	        for (Usuario usuario : usuarios) {
	            if (email.equals(usuario.getEmail()) && contrasena.equals(usuario.getContrasena())) {
	                usuarioActual = usuario;
	                break;
	            }
	        }

	        if (usuarioActual == null) {
	            JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
	            System.exit(0);
	        }

	        JOptionPane.showMessageDialog(null, "Bienvenido, " + usuarioActual.getNombre());


	        boolean salir = false;
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

	            String opcion = (String) JOptionPane.showInputDialog(null,
	                    "Seleccione una acción", "Menú", JOptionPane.PLAIN_MESSAGE,
	                    null, opciones, opciones[0]);

	            if (opcion == null || opcion.equals("Salir")) {
	                salir = true;
	                continue;
	            }

	            switch (opcion) {
	                case "Agregar producto":
	                    String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto:");
	                    String categoria = JOptionPane.showInputDialog(null, "Ingrese la categoría del producto:");
	                    double precio = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el precio del producto:"));
	                    int stock = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el stock del producto:"));
	                    String ubicacion = JOptionPane.showInputDialog(null, "Ingrese la ubicación del producto:");
	                    LocalDate fechaVto = LocalDate.parse(JOptionPane.showInputDialog(null, "Ingrese la fecha de vencimiento (YYYY-MM-DD):"));
	                    Producto nuevoProducto = new Producto(nombre, categoria, precio, fechaVto, stock, ubicacion);
	                    usuarioActual.agregarProducto(nuevoProducto);
	                    
	                    nuevoProducto.aplicarDescuento();
	                    
	                    break;

	                case "Ver productos":
	                    String productosList = "";
	                    if (usuarioActual.productos.isEmpty()) {
	                        productosList = "No hay productos para mostrar.";
	                    } else {
	                        for (Producto producto : usuarioActual.productos) {
	                            productosList += producto.toString() + "\n";
	                        }
	                    }
	                    JOptionPane.showMessageDialog(null, productosList);
	                    break;
	                    
	                case "Modificar stock de producto":
	                    if (usuarioActual instanceof Administrador || usuarioActual instanceof EncargadoDeposito) {
	                        String prodModificar = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto para modificar:");
	                        Producto productoModificado = usuarioActual.buscarProductoPorNombre(prodModificar);
	                        if (productoModificado != null) {
	                            int nuevoStock = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el nuevo stock:"));
	                            usuarioActual.modificarProducto(productoModificado, nuevoStock);
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

