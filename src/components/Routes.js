import { AppShell, Header } from "@mantine/core";
import { Route, Routes } from "react-router-dom";

import HeaderContent from "./HeaderContent";
import Homepage from "./Homepage/Homepage";

import ListTabs from "./visualization/ListTabs";
import Faq from "./SideTabItems/Faq";
import News from "./SideTabItems/News";
import HospitalList from "./SideTabItems/HospitalList";
import { CreateUser } from "./SignupLogin/CreateUser";
// import CreateUserSteps from "./SignupLogin/CreateUserSteps";
import Ambulances from "./SideTabItems/Ambulances";
// import Uploader from "./DropZone";
import NavBarRouter from "./NavBarRouter";
import Appointment from "./Appointments/Appointment";
// import ImageSlider from "./Homepage/ImageSlider";
import VerifyEmail from "./SignupLogin/VerifyEmail";
import UserProfile from "./UserProfile/UserProfile";
import UserButton from "./UserProfile/UserProfileButton";
import SignupAs from './SignupLogin/SignupAs';
import { CreateDoctor } from "./SignupLogin/CreateDoctor";
import DoctorViewAppointment from "./Appointments/DoctorViewAppointment";
import DoctorList from "./SideTabItems/DoctorList"

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
        <Route path="appointment" element={<Appointment />} />
        <Route path="viewappointment" element={<DoctorViewAppointment />} />

        <Route path="faq" element={<Faq />} />
        <Route path="ambulance" element={<Ambulances />} />
        <Route path="viz" element={<ListTabs />} />
        <Route path="hospitals" element={<HospitalList />} />
        <Route path="createuser" element={<CreateUser />} />
        <Route path="createdoctor" element={<CreateDoctor />} />
        <Route path="doclist" element={<DoctorList />} />
        {/* <Route path="imgslide" element={<ImageSlider />} /> */}
        <Route path="verifyemail" element={<VerifyEmail />} />
        <Route path="userprofile" element={<UserProfile />} />
        <Route path="signupas" element={<SignupAs />} />
        {/* <Route path="button" element={<UserProfileButton />} /> */}
      </Routes>
    </AppShell>
  );
};
export default AppBody;
