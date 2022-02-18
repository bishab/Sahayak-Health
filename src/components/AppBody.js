import { AppShell, Header } from "@mantine/core";
import { Route, Routes } from "react-router-dom";

import HeaderContent from "./HeaderContent";
import Homepage from "./Homepage";

import ListTabs from "./visualization/ListTabs";
import Faq from "./Faq";
import News from "./News";
import HospitalList from "./HospitalList";
import { AuthenticationForm } from "./SignupLogin/CreateUser";
// import CreateUserSteps from "./SignupLogin/CreateUserSteps";
import Myths from "./Myths";
// import Uploader from "./DropZone";
import NavBarRouter from "./NavBarRouter";

const AppBody = () => {
  return (
    <AppShell
      padding="md"
      navbar={<NavBarRouter />}
      header={
        <Header height={60} padding="md">
          <HeaderContent />
        </Header>
      }
    >
      <Routes>
        <Route path="/" element={<Homepage />} />
        <Route path="home" element={<Homepage />} />
        <Route path="news" element={<News />} />
        <Route path="auth" element={<AuthenticationForm />} />
        <Route path="faq" element={<Faq />} />
        <Route path="myths" element={<Myths />} />
        <Route path="viz" element={<ListTabs />} />
        <Route path="hospitals" element={<HospitalList />} />
        <Route path="createuser" element={<AuthenticationForm />} />
      </Routes>
    </AppShell>
  );
};
export default AppBody;
