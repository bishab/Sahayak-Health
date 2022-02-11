import {
  AppShell,
  Navbar,
  Header,
  Tabs,
  useMantineColorScheme,
} from "@mantine/core";
import TabMenu from "./TabMenu";
import {
  BackpackIcon,
  CalendarIcon,
  CameraIcon,
  Half1Icon,
  HomeIcon,
  QuestionMarkCircledIcon,
  RocketIcon,
  TableIcon,
} from "@modulz/radix-icons";
import HeaderContent from "./HeaderContent";
import Homepage from "./Homepage";

import ListTabs from "./visualization/ListTabs";
import Faq from "./Faq";
import News from "./News";
import HospitalList from "./HospitalList";
import CreateUser from "./SignupLogin/CreateUser";
import CreateUserSteps from "./SignupLogin/CreateUserSteps";

function Navbarx() {
  const { colorScheme, toggleColorScheme } = useMantineColorScheme();
  const dark = colorScheme === "dark";
  return (
    <AppShell
      padding="md"
      navbar={
        <Navbar padding="md">
          <Navbar.Section>
            <Tabs variant="pills" orientation="vertical">
              <Tabs.Tab label="Homepage" icon={<HomeIcon />}>
                <Homepage />
              </Tabs.Tab>
              <Tabs.Tab label="Data Visualization" icon={<CameraIcon />}>
                <ListTabs />
              </Tabs.Tab>
              <Tabs.Tab label="Book Appointment" icon={<CalendarIcon />}>
                Settings tab content
              </Tabs.Tab>

              <Tabs.Tab label="Hospitals List" icon={<TableIcon />}>
                <HospitalList />
              </Tabs.Tab>
              <Tabs.Tab label="Covid Myths" icon={<Half1Icon />}>
                Settings tab content
              </Tabs.Tab>
              <Tabs.Tab label="About Project" icon={<BackpackIcon />}>
                Settings tab content
              </Tabs.Tab>
              <Tabs.Tab label="FAQs" icon={<QuestionMarkCircledIcon />}>
                <Faq />
              </Tabs.Tab>
              <Tabs.Tab label="News " icon={<RocketIcon />}>
                <News />
              </Tabs.Tab>
              <Tabs.Tab
                label="Emergency Services "
                icon={<RocketIcon />}
                color="red"
              >
                Settings tab content
              </Tabs.Tab>

              <Tabs.Tab label="Create User " color="red">
                <CreateUserSteps />
              </Tabs.Tab>
            </Tabs>
          </Navbar.Section>
        </Navbar>
      }
      header={
        <Header height={60} padding="md">
          <HeaderContent />
        </Header>
      }
    ></AppShell>
  );
}
export default Navbarx;
