import javax.xml.bind.annotation.XmlRootElement;
import com.opencsv.bean.CsvBindByName;

@XmlRootElement
public class Cliente {
    @CsvBindByName(column = "id")  // Vincula esta columna con el campo 'id'
    private int id;
    @CsvBindByName(column = "nombre")
    private String nombre;
    @CsvBindByName(column = "dni")
    private String dni;
    @CsvBindByName(column = "email")
    private String email;
    @CsvBindByName(column = "telefono")
    private String telefono;

    public Cliente() {
    }
    public Cliente(int id, String nombre, String dni, String email, String telefono) {

        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
