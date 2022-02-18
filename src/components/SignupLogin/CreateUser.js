import React, { useState } from "react";
import Axios from "axios";
import { useForm } from "@mantine/hooks";
import {
  EnvelopeClosedIcon,
  LockClosedIcon,
  ChatBubbleIcon,
  CalendarIcon,
} from "@modulz/radix-icons";
import {
  TextInput,
  PasswordInput,
  Group,
  Checkbox,
  Button,
  Paper,
  Card,
  Text,
  LoadingOverlay,
  Anchor,
  useMantineTheme,
} from "@mantine/core";
import { DatePicker } from "@mantine/dates";
import axios from "axios";
// export interface AuthenticationFormProps {
//   noShadow?: boolean;
//   noPadding?: boolean;
//   noSubmit?: boolean;
//   style?: React.CSSProperties;
// }

export function AuthenticationForm({ noShadow, noPadding, noSubmit, style }) {
  const [formType, setFormType] = useState("register");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const theme = useMantineTheme();

  const toggleFormType = () => {
    setFormType((current) => (current === "register" ? "login" : "register"));
    setError(null);
  };

  const form = useForm({
    initialValues: {
      firstName: "",
      lastName: "",
      email: "",
      pswrd: "",
      confirmPassword: "",
      dateofbirth: "",
      address: "",
      phonenumber: "",
      termsOfService: false,
    },

    validationRules: {
      firstName: (value) => formType === "login" || value.trim().length >= 2,
      lastName: (value) => formType === "login" || value.trim().length >= 2,
      email: (value) => /^\S+@\S+$/.test(value),
      pswrd: (value) =>
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/.test(
          value
        ),
      phonenumber: (value) =>
        formType === "login" ||
        /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/.test(value),
      confirmPassword: (val, value) =>
        formType === "login" || val === value.pswrd,
    },

    errorMessages: {
      email: "Invalid email",
      phonenumber: "Invalid Phonenumber",
      pswrd:
        "Password should contain minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character",
      confirmPassword: "Passwords don't match. Try again",
    },
  });

  const handleSubmit = (values) => {
    setLoading(true);
    setError(null);

    if (formType === "register") {
      Axios.post("http://20.41.221.66:7000/postreg/", {
        first_name: values.firstName,
        last_name: values.lastName,
        email: values.email,
        password: values.pswrd,
        address: values.address,
        date_of_birth: values.dateofbirth,
        contact_number: values.phonenumber,
      })
        .then((res) => {
          setLoading(false);
          console.log("created", res);
        })
        .catch((err) => console.error(err));
    } else {
      // console.log("loginpage");
      Axios.post("http://20.41.221.66:7000/userlogin/", {
        email: values.email,
        password: values.pswrd,
      })
        .then((res) => {
          // setLoading(false);
          console.log("loggedin", res);
        })
        .catch((err) => console.error("error", err));
    }
  };

  return (
    <div style={{ width: 500, margin: "auto" }}>
      <Card shadow="sm" padding="lg">
        <Paper
          padding={noPadding ? 0 : "lg"}
          shadow={noShadow ? null : "sm"}
          style={{
            position: "relative",
            backgroundColor:
              theme.colorScheme === "dark" ? theme.colors.dark[7] : theme.white,
            ...style,
          }}
        >
          <form onSubmit={form.onSubmit(handleSubmit)}>
            {/* <LoadingOverlay visible={loading} />
            Login FOrm
            {formType === "login" && (
              <TextInput
                mt="md"
                required
                placeholder="Your email"
                label="Email"
                icon={<EnvelopeClosedIcon />}
                {...form.getInputProps("email")}
              />
            )}
            {formType === "login" && (
              <PasswordInput
                mt="md"
                required
                placeholder="Password"
                label="Password"
                icon={<LockClosedIcon />}
                {...form.getInputProps("pswrd")}
              />
            )} */}
            {/* Create user form */}
            {formType === "register" && (
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
            )}
            {(formType === "register" || formType === "login") && (
              <TextInput
                mt="md"
                required
                placeholder="Your email"
                label="Email"
                icon={<EnvelopeClosedIcon />}
                {...form.getInputProps("email")}
              />
            )}
            {(formType === "register" || formType === "login") && (
              <PasswordInput
                mt="md"
                required
                placeholder="Password"
                label="Password"
                icon={<LockClosedIcon />}
                {...form.getInputProps("pswrd")}
              />
            )}
            {formType === "register" && (
              <PasswordInput
                mt="md"
                required
                label="Confirm Password"
                placeholder="Confirm password"
                icon={<LockClosedIcon />}
                {...form.getInputProps("confirmPassword")}
              />
            )}
            {formType === "register" && (
              <TextInput
                mt="md"
                required
                placeholder="Address"
                label="Address"
                icon={<EnvelopeClosedIcon />}
                {...form.getInputProps("address")}
              />
            )}
            {formType === "register" && (
              <DatePicker
                mt="md"
                placeholder="Pick date"
                icon={<CalendarIcon />}
                label="Date of Birth"
                required
                {...form.getInputProps("dateofbirth")}
              />
            )}
            {formType === "register" && (
              <TextInput
                mt="md"
                required
                placeholder="Contact Number"
                label="Contact Number"
                icon={<ChatBubbleIcon />}
                {...form.getInputProps("phonenumber")}
              />
            )}
            {formType === "register" && (
              <Checkbox
                mt="xl"
                label="I agree to sell my soul and privacy to this corporation"
                {...form.getInputProps("termsOfService", { type: "checkbox" })}
              />
            )}
            {error && (
              <Text color="red" size="sm" mt="sm">
                {error}
              </Text>
            )}
            {!noSubmit && (
              <Group position="apart" mt="xl">
                <Anchor
                  component="button"
                  type="button"
                  color="gray"
                  onClick={toggleFormType}
                  size="sm"
                >
                  {formType === "register"
                    ? "Have an account? Login"
                    : "Don't have an account? Register"}
                </Anchor>

                <Button color="blue" type="submit">
                  {formType === "register" ? "Register" : "Login"}
                </Button>
              </Group>
            )}
          </form>
        </Paper>
      </Card>
    </div>
  );
}
