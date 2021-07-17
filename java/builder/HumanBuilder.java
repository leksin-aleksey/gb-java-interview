package builder;

public interface HumanBuilder {
    PersonBuilder setFirstName(String firstName);

    PersonBuilder setLastName(String lastName);

    PersonBuilder setMiddleName(String middleName);

    PersonBuilder setCountry(String country);

    PersonBuilder setAddress(String address);

    PersonBuilder setPhone(String phone);

    PersonBuilder setAge(int age);

    PersonBuilder setGender(String gender);

    Human build();
}
