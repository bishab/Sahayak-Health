import React from "react";
import {
  Card,
  TextInput,
  PasswordInput,
  Space,
  Grid,
  useMantineTheme,
  Title,
} from "@mantine/core";

function LoginForm() {
  const theme = useMantineTheme();

  const secondaryColor =
    theme.colorScheme === "dark" ? theme.colors.dark[1] : theme.colors.gray[7];

  return (
    <div style={{ width: 800, margin: "auto" }}>
      <Card shadow="sm" padding="lg">
        <Title order={1}>Create an Account</Title>
        {/* NAME */}
        <Space h="xl" />
        <Grid>
          <Grid.Col span={5}>
            <TextInput placeholder="Your name" label="First Name" required />
          </Grid.Col>
          <Grid.Col span={5}>
            <TextInput placeholder="Your name" label="Last Name" required />
          </Grid.Col>
        </Grid>

        <Space h="md" />

        {/* Email */}
        <TextInput placeholder="Email" label="Email" type="email" required />
        <Space h="md" />

        {/* Password */}
        <Grid>
          <Grid.Col span={5}>
            <PasswordInput placeholder="Password" label="Password" required />
          </Grid.Col>
          <Grid.Col span={5}>
            <PasswordInput
              placeholder="Re- Enter Password"
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
          required
        />
        <Space h="md" />
      </Card>
    </div>
  );
}

export default LoginForm;
