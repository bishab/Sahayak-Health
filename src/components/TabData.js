import {
  BackpackIcon,
  CalendarIcon,
  CameraIcon,
  HomeIcon,
  QuestionMarkCircledIcon,
  RocketIcon,
  TableIcon,
  ReaderIcon,
} from "@modulz/radix-icons";

const TABS = [
  {
    key: "home",
    label: "Homepage",
    icon: <HomeIcon />,
  },
  {
    key: "viz",
    label: "Data Visualization",
    icon: <CameraIcon />,
  },
  {
    key: "appointment",
    label: "Appointment",
    icon: <CalendarIcon />,
  },
  {
    key: "hospitals",
    label: "Hospitals List",
    icon: <TableIcon />,
  },
  {
    key: "docList",
    label: "Doctors in Nepal",
    icon: <BackpackIcon />,
  },
  {
    key: "ambulance",
    label: "Ambulance",
    icon: <RocketIcon />,
  },
  {
    key: "news",
    label: "News",
    icon: <ReaderIcon />,
  },
  

  {
    key: "faq",
    label: "FAQ",
    icon: <QuestionMarkCircledIcon />,
  },
 
  // {
  //   key: "createuser",
  //   label: "USER",
  //   icon: <CalendarIcon />,
  // },
];

export default TABS;
