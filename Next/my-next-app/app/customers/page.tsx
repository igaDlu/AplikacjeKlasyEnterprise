import { customerService } from "../../lib/services/customer-service";
import {createCustomerAction, deleteCustomerAction} from "./actions";
import Link from "next/link";
import SubmitButton from "@/components/submit-button"


export default async function CustomersPage() {
  const customers = await customerService.getAll();

  return (
    <>
      {/* Formularz dodawania klienta */}
      <form 
      action={createCustomerAction}
      className="flex flex-wrap gap-2 items-end">
        <div>
          <label className="block text-sm">First name</label>
          <input
            className="border px-2 py-1"
            name="firstName"
            required
          />
        </div>
        <div>
          <label className="block text-sm">Last name</label>
          <input
            className="border px-2 py-1"
            name="lastName"
            required
          />
        </div>
        <div>
          <label className="block text-sm">Email</label>
          <input
            className="border px-2 py-1"
            type="email"
            name="email"
            required
          />
        </div>
        <SubmitButton label="Submit" pendingLabel="Submit" />
      </form>

      <p>&nbsp;</p>

      {/* Tabela wyświetlająca klientów */}
      <table className="table-auto w-full text-left">
        <thead>
          <tr>
            <th className="border px-4 py-1">ID</th>
            <th className="border px-4 py-1">First name</th>
            <th className="border px-4 py-1">Last name</th>
            <th className="border px-4 py-1">Email</th>
            <th className="border px-4 py-1">Actions</th>
          </tr>
        </thead>
        <tbody>
          {customers.map((c) => (
            <tr key={c.id}>
                <Link
                    href={`/customers/${c.id}/edit`}
                    className="underline hover:text-blue-800">
                    {c.id}
                </Link>
                <td className="border px-4 py-1">{c.firstName}</td>
                <td className="border px-4 py-1">{c.lastName}</td>
                <td className="border px-4 py-1">{c.email}</td>
                <td className="border px-4 py-1">
                    <form action={deleteCustomerAction}>
                    <input type="hidden" name="id" value={c.id} />
                    <SubmitButton label="Submit" pendingLabel="DELETE" />
                    </form>
                </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
}