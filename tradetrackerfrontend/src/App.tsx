import Dashboard from "./pages/Dashboard"
import { BrowserRouter, Route, Navigate, Routes } from "react-router-dom"

function App() {

  return (
    <BrowserRouter> 
      <div className="min-h-screen flex flex-col bg-gray-50">  
        {/* Main Content */}
        <main className="flex-1 w-full">
          <Routes>
            <Route path="/" element={<Navigate to = "/dashboard" replace/>}/>
            <Route path="/dashboard" element={ <Dashboard />}/>
            <Route path="/trades"/>
            <Route path="/exceptions"/>
          </Routes>
        </main>  
      </div>      
    </BrowserRouter>  
  )
}

export default App
