import { useParams, Link } from 'react-router-dom'

function ProductDetails({ products = [] }) {
  const { id } = useParams()
  const filtered = products.filter(product => product.id.toString() === id)

  if (filtered.length === 0) {
    return null
  }

  const product = filtered[0]

  return (
    <div>
      <h1>{product.title}</h1>
      <p>
        Category: {product.category}<br />
        Brand: {product.brand}<br />
        Description: {product.description}<br />
        Price: {product.price}<br />
      </p>
      <img src={product.thumbnail} alt={product.title} />
      <div>
        <Link to="/">Back to product list</Link>
      </div>
    </div>
  )
}

export default ProductDetails
