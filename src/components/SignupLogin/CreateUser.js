import { React, useState } from "react";
import {
  Card,
  TextInput,
  PasswordInput,
  Space,
  Grid,
  useMantineTheme,
  Title,
} from "@mantine/core";

function CreateUser() {
  const theme = useMantineTheme();

  const secondaryColor =
    theme.colorScheme === "dark" ? theme.colors.dark[1] : theme.colors.gray[7];

  const initialValues = {
    firstname: "",
    lastname: "",
    email: "",
    password: "",
    password2: "",
    phone: "",
  };
  const [formValues, setFormValues] = useState(initialValues);

  return (
    <div style={{ width: 800, margin: "auto" }}>
      <Card shadow="sm" padding="lg">
        <Title order={1}>Create an Account</Title>
        {/* NAME */}
        <Space h="xl" />
        <Grid>
          <Grid.Col span={5}>
            <TextInput
              placeholder="First Name"
              name="firstname"
              label="First Name"
              required
            />
          </Grid.Col>
          <Grid.Col span={5}>
            <TextInput
              placeholder="Last Name"
              name="lastname"
              label="Last Name"
              required
            />
          </Grid.Col>
        </Grid>

        <Space h="md" />

        {/* Email */}
        <TextInput
          placeholder="Email"
          name="email"
          label="Email"
          type="email"
          required
        />
        <Space h="md" />

        {/* Password */}
        <Grid>
          <Grid.Col span={5}>
            <PasswordInput
              placeholder="Password"
              name="password"
              label="Password"
              required
            />
          </Grid.Col>
          <Grid.Col span={5}>
            <PasswordInput
              placeholder="Re- Enter Password"
              name="password2"
              label="Password"
              required
            />
          </Grid.Col>
        </Grid>
        <Space h="md" />
        {/* PhoneNumber */}

        <TextInput
          placeholder="Phone Number"
          label="Phone Number"
          type="tel"
          name="phone"
          required
        />
        <Space h="md" />
      </Card>
    </div>
  );
}

export default CreateUser;
