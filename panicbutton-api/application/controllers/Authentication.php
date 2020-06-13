<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Authentication extends CI_Controller {
    public function login()
    {
        $username   = $this->input->post('username',true);
        $password   = md5($this->input->post('password', true));

        $data       = $this->db
                        ->join('detail_pengguna dp','dp.id_detail_pengguna = p.id_detail_pengguna')
                        ->where('password',$password)->where('username',$username)->get('pengguna p');
        if($data->num_rows() > 0){
            $data = $data->row();
            
            $hasil = [
                'status'    => 'success',
                'message'   => 'Berhasil login',
                'id'      => $data->id_pengguna,
                'nama'      => $data->nama,
                'level'      => $data->id_level_akses,
            ];
            echo json_encode($hasil,JSON_PRETTY_PRINT);
            
        }else{
            
            $hasil = [
                'status' => 'failed',
                'message' => 'Gagal login',
                'id'      => null,
                'nama'      => null,
                'level'      => null,
            ];
            echo json_encode($hasil,JSON_PRETTY_PRINT);
        }
    }

    public function register()
    {
        if($this->input->post('password',true) === $this->input->post('password_confirm',true)){
            $username       = $this->input->post('username',true);
            $password       = $this->input->post('password',true);
            $nik            = $this->input->post('nik',true);
            $nama           = $this->input->post('nama',true);
            $alamat         = $this->input->post('alamat',true);
            $no_telp        = $this->input->post('no_telp',true);
            $tgl_lahir      = $this->input->post('tgl_lahir',true);
            $tempat_lahir   = $this->input->post('tempat_lahir',true);
            $foto_ktp       = $this->input->post('foto_ktp',true);
            $foto_pengguna  = $this->input->post('foto_pengguna',true);
            $level          = $this->input->post('level',true); //1 : masyarakat, 2 : polisi
            $date_created   = date('Y-m-d H:i:s');

            $this->db->trans_start();
            $data = [
                'username'          => $username,
                'password'          => md5($password),
                'id_level_akses'    => $level,
            ];

            $data_detail = [
                'nik'               => $nik,
                'nama'              => $nama,
                'alamat'            => $alamat,
                'no_telp'           => $no_telp,
                'tgl_lahir'         => date('Y-m-d',strtotime($tgl_lahir)),
                'tempat_lahir'      => $tempat_lahir,
                'foto_ktp'          => $foto_ktp,
                'foto_pengguna'     => $foto_pengguna,
            ];

            $insert     = $this->db->insert('pengguna',$data);
            if($insert){
                
                if($foto_ktp != ''){
                    $image_decoded  = base64_decode($foto_ktp);
                    $encrypt        = substr(md5_mod('salt', date('Y-m-d H:i:s')), 0, 5);
                    $image_name     = $encrypt.'.jpg';
                    if(file_put_contents(base_url('assets/img').$image_name,$image_decoded)){
                        $data_detail['foto_ktp']   = $image_name;
                    }
                }

                if($foto_pengguna != ''){
                    $image_decoded2  = base64_decode($foto_pengguna);
                    $encrypt2        = substr(md5_mod('salt', date('Y-m-d H:i:s')), 0, 5);
                    $image_name2     = $encrypt2.'.jpg';
                    if(file_put_contents(base_url('assets/img').$image_name2,$image_decoded2)){
                        $data_detail['foto_pengguna']   = $image_name2;
                    }
                }

                $insert_id      = $this->db->insert_id();
                $insert_detail  = $this->db->insert('detail_pengguna',$data_detail);
                $ins_id_detail  = $this->db->insert_id();
                $update         = $this->db
                                    ->where('id_pengguna',$insert_id)
                                    ->update('pengguna',['id_detail_pengguna'=>$ins_id_detail]);
            }
            
            $this->db->trans_complete();
            if ($this->db->trans_status() === FALSE) {
                //if something went wrong, rollback everything
                $this->db->trans_rollback();
                $hasil = [
                    'status' => 'failed',
                    'message' => 'Gagal di insert',
                ];
                echo json_encode($hasil);

            } else {
                //if everything went right, commit the data to the database
                $this->db->trans_commit();
                $hasil = [
                    'status' => 'success',
                    'message' => 'Berhasil di insert',
                ];
                echo json_encode($hasil);
            }

        }else{
            $hasil = [
                'status' => 'failed',
                'message' => 'password tidak cocok',
            ];
            echo json_encode($hasil);
        }
        
    }
}
