import {React,useState} from 'react'
import { NumberInput, Slider, Button, Group, Title, Space, Box, Paper } from '@mantine/core';
import { useForm } from '@mantine/form';
import { Card } from '@mantine/core';


function BmiCalculator() {

  const [height, setHeight] = useState(0);
  const [weight, setWeight] = useState(0);
  const [bmi, setBmi] = useState(0);
  const [status, setStatus] = useState("");
  const [color, setColor] = useState("");


const Calculation=(values)=>{
  const height = values.height;
  const weight = values.weight;
  const bmi = weight / (height /100)**2;
  console.log(bmi);
  if (bmi < 18.5) {
    setStatus("Underweight");
    setColor("red");

  } else if (bmi >= 18.5 && bmi < 25) {
    setStatus("Normal");
    setColor("blue")
  } else if (bmi >= 25 && bmi < 30) {
    setStatus("Overweight");
    setColor("orange")
  } else {
    setStatus("Obese");
    setColor("red")
  }
  setBmi(bmi);
  console.log(status);
  
}



  const form = useForm({
    initialValues: {
      height: '',
      weight: '',
    },

  });

  return (
    <div>
      <Card >
      <Space h={50} />
      
      <Title order={2}>BMI Calculator</Title>
      <form onSubmit={form.onSubmit(Calculation)}>
<NumberInput
      defaultValue={170}
      placeholder="Your Height(cm)"
      label="Your Height(cm)"
      required
      {...form.getInputProps('height')}

    />
    <NumberInput
      defaultValue={70}
      placeholder="Your Weight(kg)"
      label="Your Weight(kg)"
      required
      {...form.getInputProps('weight')}

    />
    <Group position="right" mt="md">
          <Button type="submit" >Submit</Button>
        </Group>
      <Space h="lg" />

</form>
    <Slider value={bmi} color={color} labelAlwaysOn />

    <p>Your BMI is: {bmi} </p>
            <p>You are currently: {status}</p>
            </Card>

    </div>
    
  )
}

export default BmiCalculator