package proyectofinal;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import static proyectofinal.ProyectoFinal.teclado;

public class ProyectoFinal {

    public static String telefonos[];
    public static String nombres[];
    public static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        int opcion = 0;
        String eliminar;
        nombres = new String[5];
        telefonos = new String[5];

        while (opcion != 6) {
            mostrarMenu();
            System.out.print("Ingrese opcion deseada: ");
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1:
                    do {
                        System.out.print("Ingrese nombre: ");
                        String UsuarioNombre = teclado.next();
                        System.out.print("Ingrese numero: ");
                        String UsuarioNumero = teclado.next();
                        crearUsuario(UsuarioNombre, UsuarioNumero);
                        System.out.println("Si desea agregar otro usuario ingrese 's' de lo contrario ingrese 'n'");
                        eliminar = teclado.next();
                        limpiar();
                    } while (eliminar.equals("S") || eliminar.equals("s"));
                    break;
                case 2:
                    do {
                        modificarUsuario();
                        System.out.println("Si desea modificar otro usuario ingrese 's' de lo contrario ingrese 'n'");
                        eliminar = teclado.next();
                        limpiar();
                    } while (eliminar.equals("S") || eliminar.equals("s"));
                    break;
                case 3:
                    do {
                        eliminarUsuario();
                        System.out.println("Si desea eliminar otro usuario ingrese 's' de lo contrario ingrese 'n'");
                        eliminar = teclado.next();
                        limpiar();
                    } while (eliminar.equals("S") || eliminar.equals("s"));
                    break;
                case 4:
                    do {
                        mostrarUsuario();
                        System.out.println("Ingrese 'n' para volver al menu principal");
                        eliminar = teclado.next();
                        limpiar();
                    } while (eliminar.equals("S") || eliminar.equals("s"));
                    break;
                case 5:
                    exportarArreglo(nombres, telefonos);
                    break;
                case 6:
                    break;
            }
        }

    }

    public static void mostrarMenu() {
        System.out.println("*********************************************");
        System.out.println("***************MENU PRINCIPAL****************");
        System.out.println("*********************************************");
        System.out.print("1.Registro de usuario\n"
                + "2.Modificar registro\n"
                + "3.Eliminar registro\n"
                + "4.Mostrar directorio\n"
                + "5.Exportar en documento txt\n"
                + "6.Salir\n\n");
    }

    public static void crearUsuario(String UsuarioNombre, String UsuarioNumero) {
        int cont = 0;
        if (UsuarioNombre != null && UsuarioNumero != null) {
            while (cont < nombres.length) {
                if (nombres[cont] == null) {
                    nombres[cont] = UsuarioNombre;
                    telefonos[cont] = UsuarioNumero;
                    break;
                } else if (cont + 1 >= nombres.length) {
                    System.out.print("campos llenos");
                }
                cont++;
            }
        } else {
            System.out.println("Campo vacio o nulo");
        }
    }

    public static void eliminarUsuario() {
        int cont = mostrarUsuario();
        if (cont > 0) {
            System.out.print("Ingrese el numero del usuario que desea eliminar:");
            int index = teclado.nextInt();
            if (nombres[index] != null && telefonos[index] != null) {
                System.out.println("¿Está seguro que desea eliminar el usuario: " + nombres[index] + " con numero de telefono: " + telefonos[index] + "?");
                System.out.print("Presione 's' para eliminar, presione 'n' para cancelar.");
                String eliminar = teclado.next();
                if (eliminar.equals("s") || eliminar.equals("S")) {
                    nombres[index] = null;
                    telefonos[index] = null;
                } else {
                    System.out.println("Se cancelo la eliminación");
                }
            } else {
                System.out.println("No hay usuarios para eliminar en esta posicion");
            }
        } else {
            System.out.println("No hay usuarios para eliminar");
        }
    }

    public static void modificarUsuario() {
        int cont = mostrarUsuario();
        if (cont > 0) {
            System.out.println("Ingrese la posición que desea modificar: ");
            int index = teclado.nextInt();
            if (nombres[index] != null && telefonos[index] != null) {
                System.out.print("(Nombre actual: " + nombres[index] + "). " + "Ingrese nuevo nombre: ");
                nombres[index] = teclado.next();
                System.out.print("(Numero actual = " + telefonos[index] + "). " + "Ingrese nuevo numero: ");
                telefonos[index] = teclado.next();
                System.out.println("Los nuevos datos en la posicion " + index + " son: " + nombres[index] + " " + telefonos[index] + "\n");
            } else {
                System.out.println("No hay usuarios para modificar en esta posicion");
            }
        } else {
            System.out.println("No hay usuarios para modificar");
        }
    }

    public static int mostrarUsuario() {
        int cont = 0;
        for (int i = 0; i < nombres.length; i++) {
            if (nombres[i] != null && telefonos[i] != null) {
                System.out.println("Usuario # " + i + " " + nombres[i] + " - numero: " + telefonos[i]);
                cont++;
            }
        }
        return cont;
    }

    public static void exportarArreglo(String[] nombres, String[] telefonos) throws Exception {
        try (FileWriter escribir = new FileWriter("C:/java/directorio.txt")) {
            for (int i = 0; i < nombres.length; i++) {
                if (nombres[i] == null || telefonos[i] == null) {
                    escribir.write("Usuario # " + i + " " + "VACIO(Sin registro)" + "\n");
                } else if (nombres[i] != null && telefonos[i] != null) {
                    escribir.write("Usuario # " + i + " " + "Nombre: " + nombres[i] + " - numero: " + telefonos[i] + "\n");
                }
            }
        }
        System.out.println("Se exporto la lista correctamente\n");
    }

    public static void limpiar() {
        for (int i = 0; i < 40; i++) {
            System.out.println("");
        }
    }

}
