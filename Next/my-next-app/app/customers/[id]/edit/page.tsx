import NewCustomerForm from "../../_components/NewCustomerForm";
import CustomersTable from "../../_components/CustomersTable";

export default function CustomersPage() {
  return (
    <>
      {/* Wyrenderowanie wydzielonego formularza */}
      <NewCustomerForm />

      <p>&nbsp;</p>

      {/* Wyrenderowanie wydzielonej asynchronicznej tabeli */}
      <CustomersTable />
    </>
  );
}