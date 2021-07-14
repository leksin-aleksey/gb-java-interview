package builder;

public class PersonBuilder implements Human, HumanBuilder{
    private final Person person;

    public PersonBuilder() {
        person = new Person();
    }

    @Override
    public void setFirstName(String firstName) {
        person.setFirstName(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        person.setLastName(lastName);
    }

    @Override
    public void setMiddleName(String middleName) {
        person.setMiddleName(middleName);
    }

    @Override
    public void setCountry(String country) {
        person.setCountry(country);
    }

    @Override
    public void setAddress(String address) {
        person.setAddress(address);
    }

    @Override
    public void setPhone(String phone) {
        person.setPhone(phone);
    }

    @Override
    public void setAge(int age) {
        person.setAge(age);
    }

    @Override
    public void setGender(String gender) {
        person.setGender(gender);
    }

    @Override
    public Human build() {
        return person;
    }
}
