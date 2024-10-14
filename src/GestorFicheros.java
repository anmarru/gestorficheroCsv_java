import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class GestorFicheros {

    public static void main(String[] args) {

        //fun 2
        List<Cliente> listaClientes= List.of(
                new Cliente(5,"Juan Perez","12345678A","juan.perez@mail.com", "+34600123456"),
                new Cliente(6, "Ana Torres", "23456789D", "ana.torres@mail.com", "+34600111222"),
                new Cliente(7,"Gregory","45214569F","gregory@gmail.com","96685452")

        );
        //exporto la lista de clientes
        exportarClientes(listaClientes);
        listaClientes.forEach(System.out::println);

        System.out.println("LEER LISTA DE CLIENTES ");
        //llamo la funcion 1
        List<Cliente> clientes= leerCliente();
        //imprimo cada linea
       // clientes.forEach(cliente -> System.out.println(cliente));
        //otra manera
        clientes.forEach(System.out::println);

        System.out.println("MOSTRAR ARCHIVO CSV");

        //fun 3
        //llama al leer y guardar la lista
        List<Cliente> clientes3=leerClienteCSV();
        //si la lista no es nula imprime los clientes
        if(clientes3 !=null){
            clientes3.forEach(System.out::println);
        }else{
            System.out.println("NO SE PUDO LEER EL ARCHIVO DE CLIENTE");
        }

        //FUN4
        exportarClientesCSV();


    }







    //EJERCICIO 1 leer archivo
    public static List<Cliente> leerCliente(){

        List<Cliente> listaClientes= new ArrayList<>();

        try (BufferedReader br= new BufferedReader(new FileReader("clientes.txt"))){
            String linea;
            while ((linea= br.readLine())!= null){
                //separo los campos con comas
                String[] campos= linea.split(",");

                //compruebo si el numero es español
                if(campos.length ==5 && campos[4].startsWith("+34")){
                    int id= Integer.parseInt(campos[0]);
                    String nombre = campos[1];
                    String dni= campos[2];
                    String email= campos[3].toLowerCase(); //me aseguro que el email este en minuscula
                    String telefono= campos[4];

                    //crea un nuevo objeto cliente y lo añade a la lista
                    Cliente cliente = new Cliente(id,nombre, dni, email, telefono);
                    listaClientes.add(cliente);
                }

            }
        }catch (IOException e){
            System.out.println("EARROR AL LEER EL ARCHIVO: "+ e.getMessage());
        }catch (NumberFormatException e){
            System.out.println("Error de formato en el archivo: "+ e.getMessage());
        }
        return listaClientes;
    }

    //Ejercicio 2 recibe una lista de objetos cliente y los escribe en un archivo de texto
    public static void exportarClientes(List<Cliente>listaClientes){

        try(BufferedWriter escribir= new BufferedWriter(new FileWriter("clientes.txt"))){
            for(Cliente cliente: listaClientes){
                //escribo los clientes en una linea con formato CSV
                String linea= cliente.getId()+","+ cliente.getNombre()+","+cliente.getDni()+","+cliente.getEmail()+","+cliente.getTelefono();

                escribir.write(linea);
                escribir.newLine();//escribo una nueva linea
            }
        }catch (IOException e){
            System.out.println("Error al escribir el archivo: "+ e.getMessage());
        }


    }

    //EJERCICIO3
    public static List<Cliente> leerClienteCSV(){
        List<Cliente> listaCliente= null;

        try (FileReader fileReader= new FileReader("clientes.csv")){
            //crea un CsvToBean de tipo cliente
            CsvToBean<Cliente> csvToBean= new CsvToBeanBuilder<Cliente>(fileReader)
                    .withType(Cliente.class)
                    .withIgnoreLeadingWhiteSpace(true)//ignora espacios en blanco
                    .withSeparator(',')
                    .build();

            //parsea el fichero en una lista de cliente
            listaCliente= csvToBean.parse();

        }catch (IOException e){
            System.out.println("Error al leer el archivo: "+ e.getMessage());
        }
        return listaCliente;
    }

    //Ejercicio 4
    public static  void exportarClientesCSV(){

        List<Cliente> listaClientes = new ArrayList<>();

        //agrego clientes prueba
        Collections.addAll(listaClientes,
                new Cliente(11,"Pepe","111111A","pepe@gmail.com","655895523"),
                new Cliente(12,"Ana","526898745R","aq@gmai.com","522669587"),
                new Cliente(3, "Luis", "111111", "luis@gmail.com", "635254127")
        );
        try(FileWriter writer= new FileWriter("ClientesPieba.csv")){
            //configuramos statefulBeanToCsv para escribir la lista de clientes
            StatefulBeanToCsv<Cliente> beanToCsv= new StatefulBeanToCsvBuilder<Cliente>(writer)
                    .build();
            beanToCsv.write(listaClientes);
        }catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e){
            e.getMessage();
        }
    }
}
