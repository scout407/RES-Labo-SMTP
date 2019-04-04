package Personne;

/**
 * @brief Permet de créer chaque personne qui sont des victimes potentielle
 */
public class Victim {
    private String name;
    private String firstName;
    private String email;

    public Victim(){}

    public Victim(String name, String firstName){
        this.name = name;
        this.firstName = firstName;
    }

    public Victim(String email){
        this.email = email;
    }

    public Victim(String name, String firstName, String email){
        this.name = name;
        this.firstName = firstName;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
