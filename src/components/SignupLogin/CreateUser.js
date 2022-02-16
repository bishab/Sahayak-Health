import { React, useState } from "react";
import {
  Card,
  TextInput,
  PasswordInput,
  Space,
  Grid,
  useMantineTheme,
  Title,
  Button,
  Group,
  Checkbox,
} from "@mantine/core";
import { useForm } from "@mantine/hooks";

function CreateUser() {
  const theme = useMantineTheme();

  const secondaryColor =
    theme.colorScheme === "dark" ? theme.colors.dark[1] : theme.colors.gray[7];

  const initialValues = {
    firstname: "",
    lastname: "",
    email: "",
    password: "",
    confirmpassword: "",
    phone: "",
  };
  const [formValues, setFormValues] = useState(initialValues);
  const form = useForm({
    initialValues: {
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      confirmPassword: "",
      phone: "",
      termsOfService: false,
    },

    validationRules: {
      firstName: (value) => value.trim().length >= 2,
      lastName: (value) => value.trim().length >= 2,
      email: (value) => /^\S+@\S+$/.test(value),
      password: (value) => /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/.test(value),
      confirmPassword: (val, values) => val === values.password,
    },
    errorMessages: {
      email: "Invalid email",
      password:
        "Password should contain 1 number, 1 letter and at least 6 characters",
      confirmPassword: "Passwords don't match. Try again",
    },
  });
  return (
    <div style={{ width: 800, margin: "auto" }}>
      <form onSubmit={form.onSubmit((values) => console.log(values))}>
        <Card shadow="sm" padding="lg">
          <Title order={1}>Create an Account</Title>
          {/* NAME */}
          <Space h="xl" />
          <Group grow>
            <TextInput
              data-autofocus
              required
              placeholder="Your first name"
              label="First name"
              {...form.getInputProps("firstName")}
            />

            <TextInput
              required
              placeholder="Your last name"
              label="Last name"
              {...form.getInputProps("lastName")}
            />
          </Group>
          <Space h="md" />
          {/* Email */}
          <TextInput
            required
            label="Email"
            placeholder="your@email.com"
            {...form.getInputProps("email")}
          />
          <Space h="md" />
          {/* password */}
          <PasswordInput
            mt="md"
            required
            placeholder="Password"
            label="Password"
            {...form.getInputProps("password")}
          />
          <PasswordInput
            mt="md"
            required
            label="Confirm Password"
            placeholder="Confirm password"
            {...form.getInputProps("confirmPassword")}
          />
          {/* PhoneNumber */}
          <TextInput
            placeholder="Phone Number"
            label="Phone Number"
            type="tel"
            name="phone"
            required
          />
          <Space h="md" />
          <Checkbox
            mt="md"
            label="I agree to sell my data to get you rich"
            {...form.getInputProps("termsOfService", { type: "checkbox" })}
          />
        </Card>
        <Button type="submit">Submit</Button>
      </form>
    </div>
  );
}

export default CreateUser;
