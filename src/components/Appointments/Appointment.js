import { React, useState } from "react";
import {
  Card,
  Space,
  Title,
  Select,
  Button,
  TextInput,
  useMantineTheme,
  NumberInput,
  ScrollArea,
  Grid,
} from "@mantine/core";
import { useForm } from "@mantine/hooks";
import {
  EnvelopeClosedIcon,
  HomeIcon,
  ChatBubbleIcon,
} from "@modulz/radix-icons";
import { Calendar, Clock } from "tabler-icons-react";

import { useNotifications } from "@mantine/notifications";
import { DatePicker, TimeInput } from "@mantine/dates";

import { useNavigate, useLocation } from "react-router";

import Dropzone from "./DropZone";
import Axios from "axios";

function Appointment() {
  const theme = useMantineTheme();
  const secondaryColor =
    theme.colorScheme === "dark" ? theme.colors.dark[1] : theme.colors.gray[7];
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const notifications = useNotifications();
  const navigate = new useNavigate();

  const form = useForm({
    initialValues: {
      firstname: "",
      lastname: "",
      age: 23,
      patient_email: "",
      patient_address: "",
      gender: "",
      contact_number: "",
      hospital: "",
      department: "",
      date: "",
      time: "",
      previous_reports: "",
    },

    validationRules: {
      firstname: (value) => value.trim().length >= 2,
      lastname: (value) => value.trim().length >= 2,
      patient_email: (value) =>
        /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(value),

      contact_number: (value) =>
        /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/.test(value),
    },

    errorMessages: {
      patient_email: "Invalid email",
      contact_number: "Invalid Phonenumber",
    },
  });
  const handleSubmit = (values) => {
    setLoading(true);

    setError(null);
    Axios.post("http://20.41.221.66:7000/patient/postapp/", {
      firstname: values.firstname,
      lastname: values.lastname,
      age: values.age,
      patient_email: values.patient_email,
      patient_address: values.patient_address,
      gender: values.gender,
      contact_number: values.contact_number,
      hospital: values.hospital,
      department: values.department,
      date: values.date,
      time: values.time,
    })
      .then((res) => {
        setLoading(false);
        notifications.showNotification({
          title: "Appointment booked",
          message: "Your appointment has been booked",
        });

        console.log("created", res);
      })
      .catch((err) => {
        notifications.showNotification({
          title: "Appointment Booking Failed",
          color: "red",
          message: JSON.stringify(err.response.data),
        });
        console.error(err);
      });
  };

  return (
    <>
      <ScrollArea
        style={{ height: "82vh", overflowX: "hidden" }}
        offsetScrollbars
      >
        <div style={{ width: 900, margin: "auto" }}>
          <Title order={1} align="center">
            Hospital Appointment Booking
          </Title>
          <Space h="" />

          <Card shadow="sm" padding="lg">
            <Title order={3}>Basic Details</Title>
            <Space h="md" />

            <form onSubmit={(e)=>{e.preventDefault(); handleSubmit(form.values)}}>
              <Grid grow>
                <Grid.Col md={6} lg={4}>
                  <TextInput
                    data-autofocus
                    required
                    placeholder="Your first name"
                    label="First name"
                    {...form.getInputProps("firstname")}
                  />
                </Grid.Col>
                <Grid.Col md={6} lg={4}>
                  <TextInput
                    required
                    placeholder="Your last name"
                    label="Last name"
                    {...form.getInputProps("lastname")}
                  />
                </Grid.Col>
                <Grid.Col md={6} lg={1}>
                  <NumberInput
                    placeholder="Your age"
                    label="Your age"
                    {...form.getInputProps("age")}
                    required
                  />
                </Grid.Col>
              </Grid>
              <Space h="md" />
              <Grid grow>
                <Grid.Col md={6} lg={3}>
                  <TextInput
                    mt="md"
                    required
                    placeholder="Your email"
                    label="Email"
                    icon={<EnvelopeClosedIcon />}
                    {...form.getInputProps("patient_email")}
                  />
                </Grid.Col>
                <Grid.Col md={6} lg={3}>
                  <TextInput
                    mt="md"
                    required
                    placeholder="Address"
                    label="Address"
                    icon={<HomeIcon />}
                    {...form.getInputProps("patient_address")}
                  />
                </Grid.Col>
                <Grid.Col md={6} lg={1}>
                  <Select
                    placeholder="Select One"
                    label="Gender"
                    mt="md"
                    required
                    data={[
                      { value: "Male", label: "Male" },
                      { value: "Female", label: "Female" },
                      { value: "both", label: "Both" },
                      { value: "none", label: "None" },
                    ]}
                    {...form.getInputProps("gender")}
                  />
                </Grid.Col>
              </Grid>
              <Space h="md" />
              <Grid>
                <Grid.Col md={6} lg={3}>
                  <TextInput
                    mt="md"
                    required
                    placeholder="Contact Number"
                    label="Contact Number"
                    icon={<ChatBubbleIcon />}
                    {...form.getInputProps("contact_number")}
                  />
                </Grid.Col>
              </Grid>
         
              <Grid grow>
                <Grid.Col md={6} lg={3}>
                  <Select
                    placeholder="Select One"
                    label="Hospital"
                    mt="md"
                    required
                    data={[
                      { value: "Bheri Zonal Hospital", label: "Bheri Zonal Hospital" },
                      { value: "Lumbini Provincial Hospital", label: "Lumbini Provincial Hospital" },
                      { value: "Nepal Cancer Hospital", label: "Nepal Cancer Hospital" },
                      { value: "Bhaktapur Cancer Hospital", label: "Bhaktapur Cancer Hospital" },
                      { value: "Sushil Koirala Prakhar Cancer Hospital", label: "Sushil Koirala Prakhar Cancer Hospital" },
                      { value: "Civil Service Hospital of Nepal", label: "Civil Service Hospital of Nepal	" },
                      { value: "Shukraraaj Tropical & Infectious Disease Hospital", label: "Shukraraaj Tropical & Infectious Disease Hospital	" },
                      { value: "Bir Hospital", label: "Bir Hospital" },
                      { value: "Gandaki Medical College Teaching Hospital & Research Centre", label: "Gandaki Medical College Teaching Hospital & Research Centre" },
                      { value: "National Trauma Center", label: "National Trauma Center" },
                      { value: "Vayodha Hospital", label: "Vayodha Hospital	" },
                      { value: "Dhulikhel Hospital", label: "Dhulikhel Hospital" },
                      { value: "Grande International Hospital", label: "Grande International Hospital" },
                      { value: "T.U Teaching Hospital", label: "T.U Teaching Hospital" },
                      { value: "B.P Koirala Institute of Health Science", label: "B.P Koirala Institute of Health Science" },
                      { value: "Chitwan Medical College Teaching Hospital", label: "Chitwan Medical College Teaching Hospital	" },


                    ]}
                    {...form.getInputProps("hospital")}
                  />
                </Grid.Col>
                <Grid.Col md={6} lg={2}>
                  <Select
                    placeholder="Select One"
                    label="Department"
                    mt="md"
                    required
                    data={[
                      { value: "Physiotherapy", label: "Physiotherapy" },
                      { value: "Orthopedic", label: "Orthopedic" },
                      { value: "Pediatrics", label: "Pediatrics" },
                      { value: "Psychology", label: "Psychology" },
                      { value: "Pediatrics", label: "Pediatrics" },
                      { value: "Radiology", label: "Radiology" },
                      { value: "Surgery", label: "Surgery" },
                      { value: "Gynaecology & Obstetrics", label: "Gynaecology & Obstetrics" },
                      { value: "Cardiology", label: "Cardiology" },
                      { value: "Neurology", label: "Neurology" },
                      { value: "General Medicine", label: "General Medicine" },
                      { value: "Dental", label: "Dental" },
                      { value: "Dermatology", label: "Dermatology" },
                      { value: "Ophthalmology", label: "Ophthalmology" },
                    ]}
                    {...form.getInputProps("department")}
                  />
                </Grid.Col>
                <Grid.Col md={6} lg={1}>
                  <DatePicker
                    mt="md"
                    placeholder="Pick date"
                    label="Event date"
                    icon={<Calendar size={16} />}
                    {...form.getInputProps("date")}
                  />
                </Grid.Col>

                <Grid.Col md={6} lg={1}>
                  <TimeInput
                    mt="md"
                    label="Pick time"
                    placeholder="Pick time"
                    icon={<Clock size={16} />}
                    defaultValue=""
                    {...form.getInputProps("time")}
                  />
                </Grid.Col>
              </Grid>

              <Space h="xl" />

              <Grid grow>
                <Grid.Col md={6} lg={3}>
                  <Title order={3} align="center">
                    If you have any previous report and complications
                    <Space h="sm" />
                  </Title>
                  <Dropzone {...form.getInputProps("previous_reports")} />
                </Grid.Col>
              </Grid>
              <Space h="xl" />

              <Grid grow>
                <Button 
                  variant="gradient"
                  type="submit"
                  gradient={{ from: "indigo", to: "cyan" }}
                >
                  Book Appointment
                </Button>
              </Grid>
              

            </form>
            </Card>
        </div>
      </ScrollArea>
    </>
  );
}

export default Appointment;
