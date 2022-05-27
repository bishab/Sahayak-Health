import React, { useState,useEffect } from "react";
import Axios from "axios";
import { useForm } from "@mantine/hooks";
import { useLocalStorageValue } from '@mantine/hooks';

import { useModals } from "@mantine/modals";

import {
  EnvelopeClosedIcon,
  LockClosedIcon,
  ChatBubbleIcon,
  CalendarIcon,
} from "@modulz/radix-icons";
import { ChevronDown } from 'tabler-icons-react';
import {
  TextInput,
  PasswordInput,
  Group,
  Checkbox,
  Button,
  Paper,
  Card,
  Text,
  ScrollArea,
  NativeSelect,
  LoadingOverlay,
  Anchor,
  useMantineTheme,
} from "@mantine/core";
import { useNotifications } from "@mantine/notifications";
import { DatePicker } from "@mantine/dates";
import { useNavigate, useLocation } from "react-router";

import VerifyEmail from "./VerifyEmail";

// export interface AuthenticationFormProps {
//   noShadow?: boolean;
//   noPadding?: boolean;
//   noSubmit?: boolean;
//   style?: React.CSSProperties;
// }

export function CreateUser({ noShadow, noPadding, noSubmit, style }) {
  const [formType, setFormType] = useState("register");
  const navigate = new useNavigate();
  const location = useLocation().pathname;
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const theme = useMantineTheme();
  const notifications = useNotifications();
  const modals = useModals();

  // const onClickHandler = () => {
  //   navigate(`/verifyemail`);
  // };
  
  const [userEmail, setUserEmail] = useLocalStorageValue({
    key: 'userEmail',
    defaultValue: 'sbiiigyan9@gmail.com',
  });

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
      gender:"",
      address: "",
      contactnumber: "",
      termsOfService: true,
    },

    validationRules: {
      firstName: (value) => formType === "login" || value.trim().length >= 2,
      lastName: (value) => formType === "login" || value.trim().length >= 2,
      email: (value) =>
        /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(value),
      pswrd: (value) =>
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/.test(
          value
        ),
      contactnumber: (value) =>
        formType === "login" ||
        /^(\+|00)[1-9][0-9 \-\(\)\.]{7,32}$/.test(value),
      confirmPassword: (val, value) =>
        formType === "login" || val === value.pswrd,
      termsOfService: (value) => formType === "login" || /^(true)$/.test(value),
    },

    errorMessages: {
      email: "Invalid email",
      contactnumber: "Invalid Phonenumber",
      pswrd:
        "Password should contain minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character",
      confirmPassword: "Passwords don't match. Try again",
      termsOfService: "You must accept our terms",
    },
    
  });
 


  const handleSubmit = (values) => {
    setLoading(true);
    setError(null);
    // setTimeout(() => {
    //   setLoading(false);
    //   setError(
    //     formType === "register"
    //       ? "User with this email already exists"
    //       : "User with this email does not exist"
    //   );
    // }, 3000);
    if (formType === "register") {
      Axios.post("http://20.41.221.66:7000/patient/postreg/", {
        first_name: values.firstName,
        last_name: values.lastName,
        gender:values.gender,
        email: values.email,
        password: values.pswrd,
        address: values.address,
        date_of_birth: values.dateofbirth,
        contact_number: values.contactnumber,
        
      })
        .then((res) => {
          setLoading(false);
          notifications.showNotification({
            title: "Account Created",
            message: "You can login to your account now",
          });
          console.log("created", res);

        })
        .catch((err) => {
          notifications.showNotification({
            title: "Account Creation Failed",
            color: "red",
            message: "Maybe you already have an account, use different email",
          });
          console.error(err);
        });
    } else {
      // console.log("loginpage");
      Axios.post("http://20.41.221.66:7000/userlogin/", {
        email: values.email,
        password: values.pswrd,
      })
        .then((res) => {
          // setLoading(false);
          console.log("loggedin", res.data);
            if(res.data.msg === "correct password"){
              navigate(`/userprofile`);
    }})
        .catch((err) => {
          // console.err("error", err);
          notifications.showNotification({
            title: "Login Failed",
            color: "red",
            message: "Incorrect Email or Password🤥",
          });
        });
    }
    //local storage
   setUserEmail(values.email);
    console.log("local storage ", userEmail);
  };

  return (
    <>
      <ScrollArea
        style={{ height: "82vh", overflowX: "hidden" }}
        offsetScrollbars
      >
        <div style={{ width: 500, margin: "auto" }}>
          <Card shadow="sm" padding="lg">
            <Paper
              padding={noPadding ? 0 : "lg"}
              shadow={noShadow ? null : "sm"}
              style={{
                position: "relative",
                backgroundColor:
                  theme.colorScheme === "dark"
                    ? theme.colors.dark[7]
                    : theme.white,
                ...style,
              }}
            >
              <form onSubmit={form.onSubmit(handleSubmit)}>
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
                    onFocus={() => {
                      form.validateField("pswrd");
                    }}
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
                   <NativeSelect
                   label="Gender"
                   placeholder="Your Gender"
                   data={['Male', 'Female', 'Other']}
                   rightSection={<ChevronDown size={14} />}
                   rightSectionWidth={40}
                   {...form.getInputProps("gender")}

                 />
                )}
                {formType === "register" && (
                  <TextInput
                    mt="md"
                    required
                    placeholder="Contact Number"
                    label="Contact Number"
                    icon={<ChatBubbleIcon />}
                    {...form.getInputProps("contactnumber")}
                  />
                )}
                {formType === "register" && (
                  <Checkbox
                    mt="xl"
                    label="I agree to sell my soul and privacy to this corporation"
                    onFocus={() => {
                      form.validateField("termsOfService");
                    }}
                    {...form.getInputProps("termsOfService", {
                      type: "checkbox",
                    })}
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

                    <Button color="blue" type="submit" >
                      {formType === "register" ? "Register" : "Login"}
                    </Button>
                  </Group>
                )}
              </form>
            </Paper>
          </Card>
        </div>
      </ScrollArea>
    </>
  );
}
