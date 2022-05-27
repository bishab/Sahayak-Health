import { React, useEffect, useState } from "react";
import axios from "axios";
import { useLocalStorageValue } from "@mantine/hooks";

import {
  Card,
  Group,
  SimpleGrid,
  Text,
  Space,
  Title,
  useMantineTheme,
  Container,
} from "@mantine/core";
import ProfileImage from "./ProfileImage";
function UserDetails() {
  const theme = useMantineTheme();
  let useremail = localStorage.getItem("useremail");
  console.log("in user details", useremail);

  const secondaryColor =
    theme.colorScheme === "dark" ? theme.colors.dark[1] : theme.colors.gray[7];
  const [data, setData] = useState([]);
  const userEmail = localStorage.getItem("userEmail");
  console.log("in user details", userEmail);
  useEffect(() => {
    axios
      .get(`http://20.41.221.66:7000/patient/getreg/${userEmail}/`)
      .then((response) => {
        const res = response.data;
        console.log(res);
        setData(res);
      })
      .catch((error) => console.log(error));
  }, [userEmail]);


  return (
    
      <Card  shadow="sm" p="lg">
       
          <ProfileImage/>
      <Container>
        <Title order={1}>{data[0]?.first_name} {data[0]?.last_name}</Title>
        <Space w="xl" />

        <Space h="md" />
        <Group>
          <Text weight={700}>Email:</Text>
          <Text size="lg" style={{ color: secondaryColor }}>
            {data[0]?.email}
          </Text>
        </Group>
        <Space h="sm" />
        <Group>
          <Text weight={700}>Address:</Text>
          <Text size="lg" style={{ color: secondaryColor }}>
            {data[0]?.address}
          </Text>
        </Group>
        <Space h="sm" />
        <Group>
          <Text weight={700}>Phone Number:</Text>
          <Text size="lg" style={{ color: secondaryColor }}>
            {data[0]?.contact_number}
          </Text>
        </Group>
        <Space h="sm" />
        <Group>
          <Text weight={700}>Date of Birth:</Text>
          <Text size="lg" style={{ color: secondaryColor }}>
            {data[0]?.date_of_birth}
          </Text>
        </Group>
        <Space h="sm" />
        <Group>
          <Text weight={700}>Gender:</Text>
          <Text size="lg" style={{ color: secondaryColor }}>
            {data[0]?.gender}
          </Text>
        </Group>
        </Container>
      </Card>
    
  );
}

export default UserDetails;
