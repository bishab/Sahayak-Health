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
    key: "faq",
    label: "FAQ",
    icon: <QuestionMarkCircledIcon />,
  },
  {
    key: "hospitals",
    label: "Hospitals List",
    icon: <TableIcon />,
  },
  {
    key: "news",
    label: "News",
    icon: <RocketIcon />,
  },
  {
    key: "myths",
    label: "Myths",
    icon: <Half1Icon />,
  },
  {
    key: "appointments",
    label: "Appointments",
    icon: <CalendarIcon />,
  },
  {
    key: "createuser",
    label: "USER",
    icon: <CalendarIcon />,
  },
];

export default TABS;
