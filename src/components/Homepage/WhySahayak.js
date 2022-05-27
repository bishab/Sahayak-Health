import React from "react";
import {
  Card,
  Image,
  Blockquote,
  Text,
  Badge,
  Button,
  Group,
  Title,
  Space,
  useMantineTheme,
} from "@mantine/core";

function WhySahayak() {
  const theme = useMantineTheme();

  const secondaryColor =
    theme.colorScheme === "dark" ? theme.colors.dark[1] : theme.colors.gray[7];

  return (
    <div>
      <Title align="center" order={3}>
        Why Sahayak Health
      </Title>
      <Space h="xl" />
      <div
        style={{
          display: "flex",
          flexDirection: "row",
        }}
      >
        <div style={{ width: 340, margin: "auto" }}>
          <Card shadow="sm" padding="xl">
            <Card.Section>
              <Image
                src="https://images.unsplash.com/photo-1619025873875-59dfdd2bbbd6?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687"
                height={190}
                alt="Norway"
              />
            </Card.Section>

            <Group
              position="apart"
              style={{ marginBottom: 5, marginTop: theme.spacing.sm }}
            >
              <Title order={4}>24 x 7 Ambulance Information</Title>
            </Group>

            <Text size="sm" style={{ color: secondaryColor, lineHeight: 1.5 }}>
              On the Ambulance tab, you can find the contact details of the
              Ambulance service of different places. You will no longer need to
              search for urgent contact details of ambulance in an emergency.
            </Text>
          </Card>
        </div>
        <Space w="lg" />
        <div style={{ width: 340, margin: "auto" }}>
          <Card shadow="sm" padding="xl">
            <Card.Section>
              <Image
                src="https://media.istockphoto.com/photos/thoughtful-afroamerican-person-touches-beard-on-pink-picture-id1343478786?b=1&k=20&m=1343478786&s=170667a&w=0&h=BoSyVZFBJFwlxcC1z2tzG_C0C5-DlI2f5SS1y4tPhBg="
                height={190}
                alt="Norway"
              />
            </Card.Section>

            <Group
              position="apart"
              style={{ marginBottom: 5, marginTop: theme.spacing.sm }}
            >
              <Title order={4}>Confused? Visit FAQs for Answers!</Title>
            </Group>

            <Text size="sm" style={{ color: secondaryColor, lineHeight: 1.5 }}>
              Find the frequently asked questions for Covid-19 in our FAQs
              section. You will no longer get confused over the common issues
              and problems of Covid-19 anymore.
            </Text>
          </Card>
        </div>
        <Space w="lg" />
        <div style={{ width: 340, margin: "auto" }}>
          <Card shadow="sm" padding="xl">
            <Card.Section>
              <Image
                src="https://media.istockphoto.com/photos/chatbot-ai-artificial-intelligence-technology-concept-picture-id1329694918?b=1&k=20&m=1329694918&s=170667a&w=0&h=x-vm_YVSqgVYTkongWYk7jQxSIDw9McxUfS4A3LuvWI="
                height={190}
                alt="Norway"
              />
            </Card.Section>

            <Group
              position="apart"
              style={{ marginBottom: 5, marginTop: theme.spacing.sm }}
            >
              <Title order={4}>Chat With Sahayak</Title>
            </Group>

            <Text size="sm" style={{ color: secondaryColor, lineHeight: 1.5 }}>
              Now you can chat with our conversational AI bot Sahayak to
              self-assess Covid-19 symptoms, check your appointment details, get
              the insurance information and many more.
            </Text>
          </Card>
        </div>
        <Space w="lg" />
      </div>
      <div>
        <Card shadow="sm" padding="lg"></Card>
      </div>
    </div>
  );
}

export default WhySahayak;
